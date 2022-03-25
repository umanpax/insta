package com.insta.activities.photodetails

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.insta.R
import com.insta.model.Photo
import com.insta.model.Statistics
import com.insta.utils.Application
import com.insta.utils.ApplicationConstants
import com.insta.utils.PrefsManager
import com.insta.utils.Workflow
import java.text.SimpleDateFormat

class PhotoDetailsActivity : AppCompatActivity(), PhotoDetailsView {

    private lateinit var presenter: PhotoDetailsPresenter
    private lateinit var prefsManager: PrefsManager
    private var workflow = Workflow()
    private lateinit var photo : Photo
    private lateinit var adapter: PhotoDetailsAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var listPhotos : ArrayList<Photo>
    private lateinit var constraintLayout : ConstraintLayout
    private lateinit var fbtScrollTop : FloatingActionButton
    private  var listStatistics =  ArrayList<Statistics>()
    private lateinit var listsPhotosStatistics : Pair<ArrayList<Photo>, ArrayList<Statistics>>
    private lateinit var tvPleaseWait : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_details)
        workflow = Application.getWorkflow()
        presenter = PhotoDetailsPresenter(this, workflow)
        prefsManager = PrefsManager(this)
        initViews()
        initListeners()
        getPhotoUserInformation()
    }

    private fun initViews(){
        constraintLayout = findViewById(R.id.constraint_main_photo_details)
        tvPleaseWait = findViewById(R.id.tv_please_wait)
        recyclerView = findViewById(R.id.recycler_view_photo_details)
        fbtScrollTop = findViewById(R.id.fab_scroll_up)
    }

    private fun initListeners(){
        fbtScrollTop.setOnClickListener { recyclerView.smoothScrollToPosition(0) }
    }

    private fun getPhotoUserInformation(){
        if (intent.hasExtra(ApplicationConstants.PHOTO)) {
            photo = intent.extras!!.get(ApplicationConstants.PHOTO) as Photo
            presenter.getPhotosByUserName(photo.user.username, false, ApplicationConstants.ACCESS_KEY)
            startLoader()
        }
    }

    private fun initRecycler() {
        adapter = PhotoDetailsAdapter(listsPhotosStatistics, this,presenter)
        val llm = LinearLayoutManager(this)
        llm.orientation = RecyclerView.VERTICAL
        recyclerView.visibility = View.VISIBLE
        recyclerView.layoutManager = llm
        recyclerView.adapter = adapter
        stopLoader()
    }

    override fun handlePhotosByUsername(response: Array<Photo>) {
        listPhotos = ArrayList()
        listPhotos.addAll(response)
        presenter.getPhotosStatistics(listPhotos,ApplicationConstants.ACCESS_KEY)

    }

    override fun handlePhotoStatistics(response: ArrayList<Statistics>) {
        listStatistics = response
        listsPhotosStatistics = Pair(listPhotos,listStatistics)
        initRecycler()
    }

    override fun toggleError(response: String) {
        Application.showSnackBar(this, constraintLayout, getString(R.string.error_msg))
        stopLoader()
    }

    private fun startLoader(){
        tvPleaseWait.visibility = View.VISIBLE
    }
    private fun stopLoader(){
        tvPleaseWait.visibility = View.GONE
    }
}