package com.insta.fragments.home.fragments.grid

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.insta.activities.base.BaseActivity
import com.insta.utils.Application
import com.insta.R
import com.insta.activities.photodetails.PhotoDetailsActivity
import com.insta.model.Photo
import com.insta.utils.ApplicationConstants
import com.insta.utils.PrefsManager
import com.insta.utils.Workflow

class GridFragment : Fragment() {
    private lateinit var prefsManager: PrefsManager
    private lateinit var viewModel: GridViewModel
    private var workflow = Workflow()
    private lateinit var mView: View
    private lateinit var baseActivity: BaseActivity
    private lateinit var adapterPhotos: PhotosAdapter
    private lateinit var recyclerViewDisplayPhotoAlbum: RecyclerView
    private var listPhotos = ArrayList<Photo>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        baseActivity = activity as BaseActivity
        prefsManager = PrefsManager(requireContext())
        workflow = Application.getWorkflow()
        mView = inflater.inflate(R.layout.fragment_grid, container, false)
        viewModel = GridViewModel()
        initViews(mView)
        fillViews()
        initListeners()
        initRecycler()
        return mView
    }

    private fun initRecycler() {
        listPhotos = workflow.listPhotosByUserName
        adapterPhotos = PhotosAdapter(
            listPhotos,
            baseActivity,
            viewModel
        )
        val sGridLayoutManager = StaggeredGridLayoutManager(
            3,
            StaggeredGridLayoutManager.VERTICAL
        )
        recyclerViewDisplayPhotoAlbum.visibility = View.VISIBLE
        recyclerViewDisplayPhotoAlbum.isNestedScrollingEnabled = true
        recyclerViewDisplayPhotoAlbum.layoutManager = sGridLayoutManager
        recyclerViewDisplayPhotoAlbum.adapter = adapterPhotos
        recyclerViewDisplayPhotoAlbum.scrollToPosition(0)
    }


    private fun initViews(v : View) {
        recyclerViewDisplayPhotoAlbum = v.findViewById(R.id.recycler_view_list_photo_album)
    }

    private fun initListeners() {
    }

    private fun fillViews() {

    }
}