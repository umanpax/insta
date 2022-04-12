package com.insta.activities.photodetails

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.insta.model.Photo
import com.insta.model.Statistics
import com.insta.services.ws.DataManager
import com.insta.utils.ApplicationConstants
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException
import java.util.ArrayList

class PhotoDetailsViewModel : ViewModel() {

    private lateinit var dataManagerAccessor: DataManager

    var liveDataPhotos = MutableLiveData<Array<Photo>>()
    var liveDataStatistics = MutableLiveData<Array<Statistics>>()
    var liveDataError = MutableLiveData<String>()

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
                            liveDataError.postValue(body.toString())
                        } catch (e: IOException) {
                            Log.d("test", " Error in parsing")
                        }
                    }
                }

                override fun onComplete() {
                    liveDataStatistics.postValue(listStatistics.toTypedArray())
                }
            })

    }

}
