package me.samuki.soundboard

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
internal class SoundboardApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}
