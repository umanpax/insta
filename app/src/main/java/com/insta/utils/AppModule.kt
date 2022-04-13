package com.insta.utils

import com.insta.activities.photodetails.PhotoDetailsViewModel
import com.insta.fragments.search.SearchViewModel
import com.insta.services.repositories.Repository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


class AppModule {
    companion object {
        val moduleRepository = module {
            single { Repository() }
        }
        val searchViewModel = module {
            viewModel{
                SearchViewModel()
            }
        }
        val photoViewModel = module {
            viewModel{
                PhotoDetailsViewModel()
            }
        }

    }
}