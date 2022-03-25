package com.insta.activities.splashscreen

import android.content.Intent
import android.util.Log
import androidx.core.app.ActivityCompat
import com.insta.activities.base.BaseActivity
import com.insta.model.Photo
import com.insta.model.User
import com.insta.services.ws.DataManager
import com.insta.utils.ApplicationConstants
import com.insta.utils.Workflow
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException
import java.util.*

class SplashScreenPresenter(var view: SplashScreenActivity, var workflow: Workflow) {

    private lateinit var dataManagerAccessor: DataManager

    fun getUserByUserName(username : String, token :String) {
        dataManagerAccessor = DataManager(ApplicationConstants.BASE_URL)
        dataManagerAccessor.getUserByUserName(username,token)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

            .subscribe(object : Observer<User> {

                override fun onNext(response: User) {
                    response.let { view.handleUserByUserName(response) }
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onComplete() {

                }

                override fun onError(e: Throwable) {
                    if (e is HttpException) {
                        val body = e.response()?.errorBody()
                        try {
                            view.toggleError(body.toString())
                        } catch (e: IOException) {
                            Log.d("test", " Error in parsing")

                        }
                    }
                }

            })
    }



    fun getPhotos(perPage : Int, token :String) {
        dataManagerAccessor = DataManager(ApplicationConstants.BASE_URL)
        dataManagerAccessor.getPhotos(perPage,token)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

            .subscribe(object : Observer<Array<Photo>> {

                override fun onNext(response: Array<Photo>) {
                    response.let { view.handlePhotos(it) }
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onComplete() {

                }

                override fun onError(e: Throwable) {
                    if (e is HttpException) {
                        val body = e.response()?.errorBody()
                        try {
                            view.toggleError(body.toString())
                        } catch (e: IOException) {
                            Log.d("test", " Error in parsing")

                        }
                    }
                }

            })
    }


    fun toInsta() {
        ActivityCompat.finishAffinity(view)
        val intent = Intent(view, BaseActivity::class.java)
        view.startActivity(intent)
    }


}