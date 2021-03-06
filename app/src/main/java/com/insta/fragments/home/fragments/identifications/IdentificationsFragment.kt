package com.insta.fragments.home.fragments.identifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.insta.activities.base.BaseActivity
import com.insta.utils.Application
import com.insta.R
import com.insta.utils.PrefsManager
import com.insta.utils.Workflow


class IdentificationsFragment : Fragment() {
    private lateinit var prefsManager: PrefsManager
    private lateinit var viewModel: IdentificationsViewModel
    private var workflow = Workflow()
    private lateinit var mView: View
    private lateinit var baseActivity: BaseActivity


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        baseActivity = activity as BaseActivity
        mView = inflater.inflate(R.layout.fragment_identifications, container, false)
        workflow = Application.getWorkflow()
        viewModel = IdentificationsViewModel()
        initViews()
        fillViews()
        initPrefs()
        initListeners()
        return mView
    }

    private fun initPrefs() {
        prefsManager = PrefsManager(requireContext())
    }

    private fun initListeners() {
    }

    private fun initViews() {

    }

    private fun fillViews() {

    }

}