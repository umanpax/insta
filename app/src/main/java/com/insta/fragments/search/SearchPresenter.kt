package com.insta.fragments.search

import android.content.Intent
import android.util.Log
import com.insta.activities.photodetails.PhotoDetailsActivity
import com.insta.model.Photo
import com.insta.services.ws.DataManager
import com.insta.utils.ApplicationConstants
import com.insta.utils.Workflow
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException


class SearchPresenter(
    var view: SearchFragment,
    var workflow: Workflow
) {

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
                            view.toggleError(e.code().toString())
                        } catch (e: IOException) {
                            Log.d("test", " Error in parsing")

                        }
                    }
                }

            })
    }

    fun toDetails(photo: Photo) {
        val intent = Intent(view.context, PhotoDetailsActivity::class.java)
        intent.putExtra(ApplicationConstants.PHOTO, photo)
        view.startActivity(intent)
    }

}