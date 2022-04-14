package com.insta.activities.splashscreen

import androidx.lifecycle.ViewModel
import com.insta.services.repositories.Repository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SplashScreenViewModel : ViewModel(), KoinComponent {

    val repository: Repository by inject()

    fun getUserByUserName(username : String, token :String) {
        repository.getUserByUserName(username, token)
    }

    fun getPhotos(perPage : Int, token :String) {
        repository.getPhotos(perPage, token)
    }

}