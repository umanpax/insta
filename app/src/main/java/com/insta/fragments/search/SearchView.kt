package com.insta.fragments.search

import com.insta.model.Photo

interface SearchView {
    fun handlePhotosByUsername(response : Array<Photo>)
    fun toggleError(response : String)
}