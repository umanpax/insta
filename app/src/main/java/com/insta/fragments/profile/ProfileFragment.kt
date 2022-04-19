package com.insta.fragments.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.insta.activities.base.BaseActivity
import com.insta.utils.Application
import com.insta.R
import com.insta.utils.PrefsManager
import com.insta.utils.Workflow

class ProfileFragment : Fragment() {
    private lateinit var prefsManager: PrefsManager
    private lateinit var viewModel: ProfileViewModel
    private var workflow = Workflow()
    private lateinit var mView: View
    private lateinit var baseActivity: BaseActivity
    private lateinit var tvUserName : TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        baseActivity = activity as BaseActivity
        mView = inflater.inflate(R.layout.fragment_profile, container, false)
        workflow = Application.getWorkflow()
        viewModel = ProfileViewModel()
        initData()
        initObservers()
        initViews(mView)
        fillViews()
        initPrefs()
        initListeners()
        return mView
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        baseActivity = activity as BaseActivity
    }

    private fun initPrefs() {
        prefsManager = PrefsManager(requireContext())
    }

    private fun initListeners() {
    }

    private fun initViews(v : View) {
        tvUserName = v.findViewById(R.id.tv_fragment_profile_user_name)
    }

    private fun fillViews() {

    }

    private fun initData(){
        viewModel.getUserByUserName(baseActivity,"musicpax_")
    }

    private fun initObservers(){
        viewModel.roomRepository.liveDataUser?.observe(baseActivity){ user ->
            tvUserName.text  = user.username
        }
        viewModel.roomRepository.liveDataPhotos?.observe(baseActivity){ photo ->

        }
    }
}