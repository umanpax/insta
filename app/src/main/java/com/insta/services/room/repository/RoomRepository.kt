package com.insta.services.room.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.insta.model.Photo
import com.insta.model.User
import com.insta.services.room.database.RoomDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


class RoomRepository {

        var roomDatabase: RoomDB? = null
        var liveDataUser: LiveData<User>? = null
        var liveDataPhotos: LiveData<List<Photo>>? = null

        private fun initializeDB(context: Context) : RoomDB {
            return RoomDB.getDataBase(context)
        }

        fun getUser(context: Context, username : String) : LiveData<User> {
            roomDatabase = initializeDB(context)
            liveDataUser = roomDatabase!!.instaDaoUser().getUserByUsername (username)
            return liveDataUser as LiveData<User>
        }

        fun insertUser(context: Context, user : User) {
            roomDatabase = initializeDB(context)
            CoroutineScope(IO).launch {
                roomDatabase!!.instaDaoUser().insertUser(user)
            }

        }

        fun getPhoto(context: Context, perPage : Int) : LiveData<List<Photo>> {
            roomDatabase = initializeDB(context)
            liveDataPhotos = roomDatabase!!.instaDaoPhoto().getPhotos(perPage)
           return liveDataPhotos as LiveData<List<Photo>>
        }

        fun insertPhoto(context: Context, photo : Photo) {
            roomDatabase = initializeDB(context)
            CoroutineScope(IO).launch {
                roomDatabase!!.instaDaoPhoto().insertPhoto (photo)
            }

        }

}