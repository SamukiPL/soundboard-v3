package me.samuki.soundboard

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

@HiltAndroidApp
internal class SoundboardApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Napier.base(DebugAntilog()) // TODO create initializer
    }
}
