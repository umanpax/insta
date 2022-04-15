package com.insta.fragments.profile

import android.content.Context
import androidx.lifecycle.ViewModel
import org.koin.core.component.inject
import com.insta.services.room.repository.RoomRepository
import org.koin.core.component.KoinComponent

class ProfileViewModel : ViewModel(), KoinComponent{

    val roomRepository: RoomRepository by inject()

    fun getUserByUserName(context: Context, username: String) {
        roomRepository.getUser(context,username)
    }
}