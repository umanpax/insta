package com.insta.services.repositories

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.insta.model.Photo
import com.insta.model.Statistics
import com.insta.services.ws.DataManager
import com.insta.utils.ApplicationConstants
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
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
}

