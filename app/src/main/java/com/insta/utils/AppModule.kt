package com.insta.utils

import com.insta.activities.photodetails.PhotoDetailsViewModel
import com.insta.activities.splashscreen.SplashScreenViewModel
import com.insta.fragments.search.SearchViewModel
import com.insta.services.repositories.Repository
import com.insta.services.room.repository.RoomRepository
import com.insta.services.ws.DataManager
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


class AppModule {
    companion object {
        val moduleRepository = module {
            factory { Repository() }
        }
        val moduleDataManager = module {
            factory { DataManager(ApplicationConstants.BASE_URL) }
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
        val splashViewModel = module {
            viewModel{
                SplashScreenViewModel()
            }
        }

        val instaRoomRepository = module {
            single { RoomRepository() }
        }

        val service = module {
            single { RoomRepository() }
        }

    }
}