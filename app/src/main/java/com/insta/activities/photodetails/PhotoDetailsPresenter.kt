package com.insta.activities.photodetails

import android.util.Log
import com.insta.model.Photo
import com.insta.model.Statistics
import com.insta.services.ws.DataManager
import com.insta.utils.ApplicationConstants
import com.insta.utils.Workflow
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException
import java.util.ArrayList

class PhotoDetailsPresenter(var view: PhotoDetailsActivity, var workflow: Workflow) {

    private lateinit var dataManagerAccessor: DataManager


    fun getPhotosByUserName(userName: String, stats: Boolean, token: String) {
        dataManagerAccessor = DataManager(ApplicationConstants.BASE_URL)
        dataManagerAccessor.getPhotoByUserName(userName, stats, token)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

            .subscribe(object : Observer<Array<Photo>> {

                override fun onNext(response: Array<Photo>) {
                    response.let { view.handlePhotosByUsername(it) }
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
                            view.toggleError(body.toString())
                        } catch (e: IOException) {
                            Log.d("test", " Error in parsing")

                        }
                    }
                }

                override fun onComplete() {
                    view.handlePhotoStatistics(listStatistics)
                }
            })

    }

}
