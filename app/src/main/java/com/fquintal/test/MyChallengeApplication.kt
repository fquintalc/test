package com.fquintal.test

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyChallengeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}