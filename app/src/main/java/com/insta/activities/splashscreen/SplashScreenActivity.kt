package com.insta.activities.splashscreen

import android.annotation.SuppressLint
import android.os.*
import android.os.StrictMode
import androidx.activity.ComponentActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.insta.R
import com.insta.model.Photo
import com.insta.model.User
import com.insta.utils.Application
import com.insta.utils.ApplicationConstants
import com.insta.utils.PrefsManager
import com.insta.utils.Workflow


/**
 * Created by pierre-alexandrevezinet on 24/03/2022.
 */

class SplashScreenActivity : ComponentActivity(), SplashScreenView {

    private val STOPSPLASH = 0
    private lateinit var presenter: SplashScreenPresenter
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
        presenter = SplashScreenPresenter(this, workflow)
        prefsManager = PrefsManager(this)
        val msg = Message()
        msg.what = STOPSPLASH
        splashHandler.sendMessageDelayed(msg, SPLASHTIME)
        presenter.getUserByUserName(ApplicationConstants.USERNAME, ApplicationConstants.ACCESS_KEY)
    }

    override fun handleUserByUserName(response: User) {
        Workflow.Singleton.INSTANCE = Workflow()
        workflow.user = response
        presenter.getPhotos(20, ApplicationConstants.ACCESS_KEY)
    }

    override fun handlePhotos(response: Array<Photo>) {
        workflow.listPhotosByUserName.addAll(response)
        Workflow.Singleton.INSTANCE!!.getInstance().updateInstance(workflow)
        presenter.toInsta()
    }

    override fun toggleError(response: String) {
        Application.showSnackBar(this, constraintLayout, getString(R.string.error_msg))
    }
}