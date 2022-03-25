package com.insta.activities.photodetails

import com.insta.model.Photo
import com.insta.model.Statistics

interface PhotoDetailsView {
    fun handlePhotosByUsername(response : Array<Photo>)
    fun handlePhotoStatistics(response : ArrayList<Statistics>)
    fun toggleError(response : String)
}
