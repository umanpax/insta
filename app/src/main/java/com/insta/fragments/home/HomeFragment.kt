package com.insta.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.insta.ui.base.BaseActivity
import com.insta.utils.Application
import com.insta.R
import com.insta.fragments.home.fragments.grid.GridFragment
import com.insta.fragments.home.fragments.identifications.IdentificationsFragment
import com.insta.fragments.home.fragments.igtv.IgtvFragment
import com.insta.utils.PrefsManager
import com.insta.utils.Workflow

class HomeFragment : Fragment() {
    private lateinit var prefsManager: PrefsManager
    private lateinit var viewModel: HomeViewModel
    private var workflow = Workflow()
    private lateinit var mView: View
    private lateinit var baseActivity: BaseActivity
    private lateinit var tabLayout: TabLayout
    private lateinit var gridFragment: GridFragment
    private lateinit var igtvFragment: IgtvFragment
    private lateinit var identificationsFragment: IdentificationsFragment
    private var listFragments = ArrayList<Fragment>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        baseActivity = activity as BaseActivity
        prefsManager = PrefsManager(requireContext())
        workflow = Application.getWorkflow()
        mView = inflater.inflate(R.layout.fragment_home, container, false)
        viewModel = HomeViewModel()
        initFragments()
        initBaseTabs(mView)
        return mView
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        baseActivity = activity as BaseActivity
    }

    private fun initFragments() {
        gridFragment = GridFragment()
        igtvFragment = IgtvFragment()
        identificationsFragment = IdentificationsFragment()

        listFragments.add(gridFragment)
        listFragments.add(igtvFragment)
        listFragments.add(identificationsFragment)
    }


    private fun initBaseTabs(v: View) {
        tabLayout = v.findViewById(R.id.tab_layout)

        tabLayout.apply {
            addTab(tabLayout.newTab())
            addTab(tabLayout.newTab())
            addTab(tabLayout.newTab())
            getTabAt(0)?.setCustomView(R.layout.tab_view_grid)
            getTabAt(1)?.setCustomView(R.layout.tab_view_igtv)
            getTabAt(2)?.setCustomView(R.layout.tab_view_identifications)
            tabGravity = TabLayout.GRAVITY_FILL
        }

        val viewPager = v.findViewById<View>(R.id.view_pager_home) as ViewPager
        viewPager.offscreenPageLimit = 3
        val tabsAdapter = HomeTabsAdapter(baseActivity.supportFragmentManager, tabLayout.tabCount,
            listFragments)
        viewPager.adapter = tabsAdapter
        viewPager.currentItem = 0
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }
            override fun onTabUnselected(tab: TabLayout.Tab) {
            }

            override fun onTabReselected(tab: TabLayout.Tab) {
            }
        })
    }
}