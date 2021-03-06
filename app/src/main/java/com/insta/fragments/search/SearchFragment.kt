package com.insta.fragments.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.insta.activities.base.BaseActivity
import com.insta.utils.Application
import com.insta.R
import com.insta.model.Photo
import com.insta.utils.ApplicationConstants
import com.insta.utils.PrefsManager
import com.insta.utils.Workflow
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {
    private lateinit var prefsManager: PrefsManager
    private var workflow = Workflow()
    private lateinit var mView: View
    private lateinit var baseActivity: BaseActivity
    private lateinit var adapter: SearchPhotoAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var etSearch: EditText
    private lateinit var tvSearch_status: TextView
    private lateinit var imvRemoveText: ImageView
    private lateinit var buttonSearch: Button
    private lateinit var constraintLayout: ConstraintLayout
    private lateinit var listPhotos: ArrayList<Photo>
    private val searchViewModel by viewModel<SearchViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        baseActivity = activity as BaseActivity
        mView = inflater.inflate(R.layout.fragment_search, container, false)
        workflow = Application.getWorkflow()
        prefsManager = PrefsManager(requireContext())
        initViews(mView)
        initListeners()
        initObservers()
        return mView
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        baseActivity = activity as BaseActivity
    }


    private fun initViews(v: View) {
        constraintLayout = v.findViewById(R.id.constraint_search_main)
        tvSearch_status = v.findViewById(R.id.tv_msg_searching)
        etSearch = v.findViewById(R.id.et_search)
        imvRemoveText = v.findViewById(R.id.imv_delete_text)
        buttonSearch = v.findViewById(R.id.btn_search)
        recyclerView = v.findViewById(R.id.recycler_view_photo_details)
    }

    private fun initListeners() {
        buttonSearch.setOnClickListener {
            if (etSearch.text.isNotEmpty()) {
                searchViewModel.getPhotosByUserName(etSearch.text.toString(), false, ApplicationConstants.ACCESS_KEY)
                tvSearch_status.text = getString(R.string.searching_msg)
                tvSearch_status.visibility = View.VISIBLE
            } else {
                Application.showSnackBar(baseActivity, constraintLayout, getString(R.string.empty_search))
                tvSearch_status.visibility = View.GONE
            }
        }
        imvRemoveText.setOnClickListener { etSearch.text.clear() }
    }


    private fun initObservers(){
        searchViewModel.repository.liveDataPhotos.observe(baseActivity){ photos ->
            listPhotos = ArrayList()
            listPhotos.addAll(photos)
            tvSearch_status.text = baseActivity.getString(R.string.count_results_search,listPhotos.size.toString())
            tvSearch_status.visibility = View.VISIBLE
            initRecycler()
        }

        searchViewModel.repository.liveDataError.observe(baseActivity){error ->
            if(error == "404"){
                listPhotos = ArrayList()
                initRecycler()
                tvSearch_status.text = baseActivity.getString(R.string.count_results_search,listPhotos.size.toString())
                tvSearch_status.visibility = View.VISIBLE
            }else{
                Application.showSnackBar(baseActivity, constraintLayout, getString(R.string.error_msg))
                tvSearch_status.visibility = View.GONE
            }
        }
    }

    private fun initRecycler() {
        adapter = SearchPhotoAdapter(listPhotos, baseActivity, searchViewModel)
        val llm = LinearLayoutManager(baseActivity)
        llm.orientation = RecyclerView.VERTICAL
        recyclerView.visibility = View.VISIBLE
        recyclerView.layoutManager = llm
        recyclerView.adapter = adapter
    }

}