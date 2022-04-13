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
import com.insta.fragments.search.SearchViewModel
import com.insta.model.Photo
import com.insta.model.Statistics
import com.insta.utils.Application
import com.insta.utils.ApplicationConstants
import com.insta.utils.PrefsManager
import com.insta.utils.Workflow
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class PhotoDetailsActivity : AppCompatActivity() {

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

    private val photoViewModel by viewModel<PhotoDetailsViewModel>()
    private val searchViewModel by viewModel<SearchViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photo_details)
        workflow = Application.getWorkflow()
        prefsManager = PrefsManager(this)

        initViews()
        initListeners()
        initObservers()
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
            searchViewModel.getPhotosByUserName(photo.user.username, false, ApplicationConstants.ACCESS_KEY)
            startLoader()
        }
    }

    private fun initObservers(){
        searchViewModel.liveDataPhotos.observe(this){photos ->
            listPhotos = ArrayList()
            listPhotos.addAll(photos)
            photoViewModel.getPhotosStatistics(listPhotos,ApplicationConstants.ACCESS_KEY)
        }

        photoViewModel.liveDataStatistics.observe(this){statistics ->
            listStatistics = statistics.toMutableList() as ArrayList<Statistics>
            listsPhotosStatistics = Pair(listPhotos,listStatistics)
            initRecycler()
        }

        searchViewModel.liveDataError.observe(this){error ->
            Application.showSnackBar(this, constraintLayout, getString(R.string.error_msg))
            stopLoader()
        }

        photoViewModel.liveDataError.observe(this){error ->
            Application.showSnackBar(this, constraintLayout, getString(R.string.error_msg))
            stopLoader()
        }
    }

    private fun initRecycler() {
        adapter = PhotoDetailsAdapter(listsPhotosStatistics, this,photoViewModel)
        val llm = LinearLayoutManager(this)
        llm.orientation = RecyclerView.VERTICAL
        recyclerView.visibility = View.VISIBLE
        recyclerView.layoutManager = llm
        recyclerView.adapter = adapter
        stopLoader()
    }

    private fun startLoader(){
        tvPleaseWait.visibility = View.VISIBLE
    }
    private fun stopLoader(){
        tvPleaseWait.visibility = View.GONE
    }
}