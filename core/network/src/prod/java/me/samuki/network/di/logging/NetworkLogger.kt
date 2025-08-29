package me.samuki.network.di.logging

import android.util.Log
import io.ktor.client.plugins.logging.Logger
import javax.inject.Inject

internal class NetworkLogger @Inject constructor() : Logger {

    override fun log(message: String) {
        Log.d(NetworkLogger::class.simpleName, message)
    }
}
