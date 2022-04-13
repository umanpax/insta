package com.insta.activities.splashscreen

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.insta.model.Photo
import com.insta.model.User
import com.insta.services.ws.DataManager
import com.insta.utils.ApplicationConstants
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException

class SplashScreenViewModel : ViewModel() {

    private lateinit var dataManagerAccessor: DataManager

    var liveDataUser = MutableLiveData<User>()
    var liveDataPhotos = MutableLiveData<Array<Photo>>()
    var liveDataError = MutableLiveData<String>()

    fun getUserByUserName(username : String, token :String) {
        dataManagerAccessor = DataManager(ApplicationConstants.BASE_URL)
        dataManagerAccessor.getUserByUserName(username,token)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

            .subscribe(object : Observer<User> {

                override fun onNext(response: User) {
                    liveDataUser.postValue(response)
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onComplete() {

                }

                override fun onError(e: Throwable) {
                    if (e is HttpException) {
                        val body = e.response()?.errorBody()
                        try {
                            liveDataError.postValue(body.toString())
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
                    response.let {
                        liveDataPhotos.postValue(it)
                    }
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onComplete() {

                }

                override fun onError(e: Throwable) {
                    if (e is HttpException) {
                        val body = e.response()?.errorBody()
                        try {
                            liveDataError.postValue(body.toString())
                        } catch (e: IOException) {
                            Log.d("test", " Error in parsing")

                        }
                    }
                }

            })
    }
}