package com.insta.utils

import android.app.Application
import org.koin.core.context.startKoin

class KoinApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        // start koin
        startKoin {
            modules(AppModule.moduleUser)
            modules(AppModule.moduleListPhotos)
            modules(AppModule.viewModels)
        }
    }
}