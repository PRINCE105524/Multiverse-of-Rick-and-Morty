package com.priem.multiverseofrickandmorty

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RickyandMortyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}