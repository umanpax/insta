package com.insta.activities.photodetails

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
    private val sdf = SimpleDateFormat("yyyy-mm-dd HH:mm:ss")
    private var workflow = Workflow()
    private lateinit var photo : Photo
    private lateinit var adapter: PhotoDetailsAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var listPhotos : ArrayList<Photo>
    private lateinit var statistics: Statistics
    private lateinit var constraintLayout : ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_details)
        initViews()
        workflow = Application.getWorkflow()
        prefsManager = PrefsManager(this)
        getPhotoUserInformation()
        presenter = PhotoDetailsPresenter(this, workflow)
        presenter.getPhotosByUserName("paxvez", true, ApplicationConstants.ACCESS_KEY)

    }

    private fun initViews(){
        constraintLayout = findViewById(R.id.constraint_main_photo_details)
        recyclerView = findViewById(R.id.recycler_view_photo_details)
    }

    private fun getPhotoUserInformation(){
        if (intent.hasExtra(ApplicationConstants.PHOTO)) {
            photo = intent.extras!!.get("join_piece") as Photo
        }
    }

    fun initRecycler() {
        adapter = PhotoDetailsAdapter(listPhotos, this,presenter)
        val llm = LinearLayoutManager(this)
        llm.orientation = RecyclerView.VERTICAL
        recyclerView.visibility = View.VISIBLE
        recyclerView.layoutManager = llm
        recyclerView.adapter = adapter
    }

    override fun handlePhotosByUsername(response: Array<Photo>) {
        listPhotos = ArrayList()
        listPhotos.addAll(response)
        initRecycler()
    }

    override fun handlePhotoStatistics(response: Statistics) {
        statistics = response
    }

    override fun toggleError(response: String) {
        Application.showSnackBar(this, constraintLayout, getString(R.string.error_msg))
    }

}