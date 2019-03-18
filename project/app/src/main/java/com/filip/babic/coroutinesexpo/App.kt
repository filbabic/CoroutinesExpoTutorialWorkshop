package com.filip.babic.coroutinesexpo

import android.app.Application
import com.filip.babic.coroutinesexpo.di.AppModule
import com.filip.babic.coroutinesexpo.di.NetworkingModule
import com.filip.babic.coroutinesexpo.di.PresentationModule
import com.google.firebase.FirebaseApp
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)

        startKoin(this, listOf(AppModule, NetworkingModule, PresentationModule))
    }
}