package me.samuki.network.di.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import me.samuki.network.di.logging.NetworkLogger

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    fun httpClient(
        networkLogger: NetworkLogger
    ): HttpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json()
        }
        install(Logging) {
            logger = networkLogger
            level = LogLevel.ALL
        }

        engine {
            connectTimeout = 10_000
        }
    }
}
