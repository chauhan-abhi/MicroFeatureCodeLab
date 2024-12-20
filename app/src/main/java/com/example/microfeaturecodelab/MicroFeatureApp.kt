package com.example.microfeaturecodelab

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MicroFeatureApp : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}