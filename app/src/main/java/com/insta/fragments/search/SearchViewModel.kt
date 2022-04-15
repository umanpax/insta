package com.insta.fragments.search
import androidx.lifecycle.ViewModel
import com.insta.services.repositories.Repository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class SearchViewModel : ViewModel(), KoinComponent {

    val repository: Repository by inject()

    fun getPhotosByUserName(userName: String, stats: Boolean, token: String) {
        repository.getPhotosByUserName(userName, stats, token)
    }
}