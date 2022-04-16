package com.insta.ui.photodetails

import androidx.lifecycle.ViewModel
import com.insta.model.Photo
import com.insta.services.repositories.Repository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import java.util.ArrayList

class PhotoDetailsViewModel : ViewModel(), KoinComponent {

    val repository: Repository by inject()

    fun getPhotosStatistics(listPhotos: ArrayList<Photo>, token: String) {
        repository.getPhotosStatistics(listPhotos, token)
    }
}
