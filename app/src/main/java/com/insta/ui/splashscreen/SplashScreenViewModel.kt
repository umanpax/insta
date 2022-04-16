package com.insta.ui.splashscreen

import android.content.Context
import androidx.lifecycle.ViewModel
import com.insta.model.Photo
import com.insta.model.User
import com.insta.services.repositories.Repository
import com.insta.services.room.repository.RoomRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SplashScreenViewModel : ViewModel(), KoinComponent {

    val repository: Repository by inject()
    val roomRepository: RoomRepository by inject()

    fun getUserByUserName(username : String, token :String) {
        repository.getUserByUserName(username, token)
    }

    fun getPhotos(perPage : Int, token :String) {
        repository.getPhotos(perPage, token)
    }

    fun insertUser(context: Context, user :User){
        roomRepository.insertUser(context,user)
    }

    fun insertPhoto(context: Context, photo: Photo){
        roomRepository.insertPhoto(context,photo)
    }

}