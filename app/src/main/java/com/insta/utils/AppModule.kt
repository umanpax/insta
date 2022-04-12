package com.insta.utils

import com.insta.activities.photodetails.PhotoDetailsViewModel
import com.insta.model.Photo
import com.insta.model.User
import org.koin.dsl.module

class AppModule {
    companion object {
        val moduleUser = module {
            single { User() }
        }
        val moduleListPhotos = module {
            single { ArrayList<Photo>() }
        }

        val viewModels = module {
            factory{
                PhotoDetailsViewModel()
            }
        }
    }
}