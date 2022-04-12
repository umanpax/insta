package com.insta.fragments.search

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.insta.model.Photo
import com.insta.services.ws.DataManager
import com.insta.utils.ApplicationConstants
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import java.io.IOException


class SearchViewModel : ViewModel() {

    private lateinit var dataManagerAccessor: DataManager
    var liveDataPhotos = MutableLiveData<Array<Photo>>()
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
                        try {
                            liveDataError.postValue(e.code().toString())
                        } catch (e: IOException) {
                            Log.d("test", " Error in parsing")
                        }
                    }
                }
            })
    }

}