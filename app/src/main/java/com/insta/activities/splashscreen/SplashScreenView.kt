package com.insta.activities.splashscreen

import com.insta.model.Photo
import com.insta.model.User


interface SplashScreenView{
    fun handleUserByUserName(response : User)
    fun handlePhotos(response : Array<Photo>)
    fun toggleError(response : String)
}