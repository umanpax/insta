package com.insta.ui.base

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.insta.utils.Application
import com.insta.R
import com.insta.fragments.add.AddFragment
import com.insta.fragments.home.HomeFragment
import com.insta.fragments.likes.LikesFragment
import com.insta.fragments.profile.ProfileFragment
import com.insta.fragments.search.SearchFragment
import com.insta.utils.Workflow


open class BaseActivity : AppCompatActivity() {

    private lateinit var viewModel: BaseViewModel
    protected var workflow = Workflow()
    lateinit var tabLayout: TabLayout
    private lateinit var homeFragment: HomeFragment
    private lateinit var searchFragment: SearchFragment
    private lateinit var addFragment: AddFragment
    private lateinit var likesFragment: LikesFragment
    private lateinit var profileFragment: ProfileFragment
    private var listFragments = ArrayList<Fragment>()
    private lateinit var tvUsername : TextView
    private lateinit var imvBack : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base)
        Application.changeStatusBarColor(this, R.color.colorWhite)
        workflow = Application.getWorkflow()
        viewModel = BaseViewModel()
        initViews()
        fillViews()
        initFragments()
        initBaseTabs()
        initBaseListeners()
    }

    private fun initFragments() {
        homeFragment = HomeFragment()
        searchFragment = SearchFragment()
        addFragment = AddFragment()
        likesFragment = LikesFragment()
        profileFragment = ProfileFragment()
        listFragments.add(homeFragment)
        listFragments.add(searchFragment)
        listFragments.add(addFragment)
        listFragments.add(likesFragment)
        listFragments.add(profileFragment)
    }

    private fun initViews() {
        tvUsername = findViewById(R.id.tv_username)
        tabLayout = findViewById(R.id.tab_layout)
        imvBack = findViewById(R.id.imv_back)
    }

    private fun fillViews(){
        tvUsername.text = workflow.user.username
    }

    private fun initBaseListeners() {
        imvBack.setOnClickListener{finish()}
    }

    private fun initBaseTabs() {
        tabLayout.apply {
            addTab(tabLayout.newTab())
            addTab(tabLayout.newTab())
            addTab(tabLayout.newTab())
            addTab(tabLayout.newTab())
            addTab(tabLayout.newTab())
            getTabAt(0)?.setCustomView(R.layout.tab_view_home)
            getTabAt(1)?.setCustomView(R.layout.tab_view_search)
            getTabAt(2)?.setCustomView(R.layout.tab_view_add)
            getTabAt(3)?.setCustomView(R.layout.tab_view_likes)
            getTabAt(4)?.setCustomView(R.layout.tab_view_profile)
            tabGravity = TabLayout.GRAVITY_FILL
        }

        val viewPager = findViewById<View>(R.id.view_pager_base) as ViewPager
        viewPager.offscreenPageLimit = 5
        val tabsAdapter = BaseTabsAdapter(supportFragmentManager, tabLayout.tabCount,
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


