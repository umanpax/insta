package com.insta.activities.splashscreen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.*
import android.os.StrictMode
import androidx.activity.ComponentActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import com.insta.R
import com.insta.activities.base.BaseActivity
import com.insta.model.Photo
import com.insta.utils.*


/**
 * Created by pierre-alexandrevezinet on 24/03/2022.
 */

class SplashScreenActivity : ComponentActivity() {

    private val STOPSPLASH = 0
    private lateinit var viewModel: SplashScreenViewModel
    private lateinit var prefsManager: PrefsManager
    private lateinit var constraintLayout: ConstraintLayout
    private var workflow = Workflow()
    private val SPLASHTIME: Long = 2000


    @Transient
    private val splashHandler = @SuppressLint("HandlerLeak")
    object : Handler() {
        @SuppressLint("ResourceType")
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }
    }

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)
        Application.changeStatusBarColor(this, R.color.colorWhite)
        constraintLayout = findViewById(R.id.splash_layout)
        viewModel = SplashScreenViewModel()
        prefsManager = PrefsManager(this)
        val msg = Message()
        msg.what = STOPSPLASH
        splashHandler.sendMessageDelayed(msg, SPLASHTIME)
        Workflow.Singleton.INSTANCE = Workflow()
        initData()
        initObservers()

    }

    private fun initData() {
        viewModel.getUserByUserName(ApplicationConstants.USERNAME, ApplicationConstants.ACCESS_KEY)
    }

    @SuppressLint("SetTextI18n")
    private fun initObservers() {
        viewModel.liveDataUser.observe(this) { user ->
            workflow.user = user
            viewModel.getPhotos(20, ApplicationConstants.ACCESS_KEY)
        }
        viewModel.liveDataPhotos.observe(this) { photos ->
            workflow.listPhotosByUserName = photos.toMutableList() as ArrayList<Photo>
            Workflow.Singleton.INSTANCE!!.getInstance().updateInstance(workflow)
            toInsta()
        }

        viewModel.liveDataError.observe(this){ error ->
            Application.showSnackBar(this, constraintLayout, getString(R.string.error_msg))
        }
    }

    private fun toInsta() {
        ActivityCompat.finishAffinity(this)
        val intent = Intent(this, BaseActivity::class.java)
        startActivity(intent)
    }

}