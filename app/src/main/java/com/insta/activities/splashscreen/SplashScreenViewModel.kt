package com.insta.activities.splashscreen

import android.content.Context
import androidx.lifecycle.ViewModel
import com.insta.model.Photo
import com.insta.model.User
import com.insta.services.repositories.Repository
import com.insta.services.room.InstaRepositoryRoom
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

    fun insertUserRoom(context: Context, user: User) {
        InstaRepositoryRoom.insertUser(context,user)
    }

    fun insertPhotoRoom(context: Context, photo: Photo) {
        InstaRepositoryRoom.insertPhoto(context,photo)
    }

}