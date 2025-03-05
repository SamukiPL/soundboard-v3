package me.samuki.network.di.logging

import io.github.aakira.napier.Napier
import io.ktor.client.plugins.logging.Logger
import javax.inject.Inject

internal class NetworkLogger @Inject constructor() : Logger {

    override fun log(message: String) = Napier.d { message }
}
