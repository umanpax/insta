package com.insta.fragments.home.fragments.grid

import android.content.Intent
import com.insta.activities.photodetails.PhotoDetailsActivity
import com.insta.model.Photo
import com.insta.utils.ApplicationConstants
import com.insta.utils.Workflow

class GridPresenter(
    var view: GridFragment,
    var workflow: Workflow
) {

    fun toDetails(photo: Photo) {
        val intent = Intent(view.context, PhotoDetailsActivity::class.java)
        intent.putExtra(ApplicationConstants.PHOTO, photo)
        view.startActivity(intent)
    }
}