package com.insta.utils

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class KoinApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        // start koin
        startKoin {
            androidContext(this@KoinApplication)
            modules(listOf(AppModule.moduleViewModel,AppModule.moduleRepository))
        }
    }
}