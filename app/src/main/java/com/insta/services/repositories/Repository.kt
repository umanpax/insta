package com.insta.services.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.insta.model.Photo
import com.insta.model.Statistics
import com.insta.model.User
import com.insta.services.ws.DataManager
import com.insta.utils.ApplicationConstants
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException
import java.util.ArrayList

class Repository {

    private lateinit var dataManagerAccessor: DataManager
    val liveDataStatistics = MutableLiveData<Array<Statistics>>()
    val liveDataError = MutableLiveData<String>()


    fun getPhotosStatistics(
        listPhotos: ArrayList<Photo>,
        token: String
    ) {

        val listStatistics = ArrayList<Statistics>()
        dataManagerAccessor = DataManager(ApplicationConstants.BASE_URL)
        io.reactivex.Observable.fromIterable(listPhotos.toMutableList())
            .flatMap { dataManagerAccessor.getPhotoStatisticsById(it.id, token) }
            .subscribe(object : Observer<Statistics> {
                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(response: Statistics) {
                    listStatistics.add(response)
                }

                override fun onError(e: Throwable) {
                    if (e is HttpException) {
                        val body = e.response()?.errorBody()
                        try {
                            liveDataError.postValue( body.toString())
                        } catch (e: IOException) {
                            Log.d("test", " Error in parsing")
                        }
                    }
                }

                override fun onComplete() {
                    liveDataStatistics.postValue( listStatistics.toTypedArray())
                }
            })

    }


    val liveDataPhotos = MutableLiveData<Array<Photo>>()

    fun getPhotosByUserName(userName: String, stats: Boolean, token: String) {
        dataManagerAccessor = DataManager(ApplicationConstants.BASE_URL)
        dataManagerAccessor.getPhotoByUserName(userName, stats, token)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

            .subscribe(object : Observer<Array<Photo>> {

                override fun onNext(response: Array<Photo>) {
                    liveDataPhotos.postValue(response)
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onComplete() {

                }

                override fun onError(e: Throwable) {
                    if (e is HttpException) {
                        try {
                            liveDataError.postValue(e.code().toString())
                        } catch (e: IOException) {
                            Log.d("test", " Error in parsing")
                        }
                    }
                }
            })
    }


    var liveDataUser = MutableLiveData<User>()

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

