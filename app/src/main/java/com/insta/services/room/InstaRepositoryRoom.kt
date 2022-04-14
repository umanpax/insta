package com.insta.services.room

import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.insta.model.Photo
import com.insta.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class InstaRepositoryRoom {

    companion object {

        var instaDatabase: InstaDatabase? = null

        var userName: MutableLiveData<User>? = null

        private fun initializeDB(context: Context) : InstaDatabase {
            return InstaDatabase.getDataBase(context)
        }

        fun getUser(context: Context, username : String) {
            instaDatabase = initializeDB(context)
            CoroutineScope(IO).launch {
                instaDatabase!!.instaDao().getUserByUsername (username)
            }

        }

        fun insertUser(context: Context, user : User) {
            instaDatabase = initializeDB(context)
            CoroutineScope(IO).launch {
                instaDatabase!!.instaDao().insertUser (user)
            }

        }

        fun getPhoto(context: Context, perPage : Int) {
            instaDatabase = initializeDB(context)
            CoroutineScope(IO).launch {
                instaDatabase!!.instaDao().getPhotos(perPage)
            }

        }

        fun insertPhoto(context: Context, photo : Photo) {
            instaDatabase = initializeDB(context)
            CoroutineScope(IO).launch {
                instaDatabase!!.instaDao().insertPhoto (photo)
            }

        }

    }
}