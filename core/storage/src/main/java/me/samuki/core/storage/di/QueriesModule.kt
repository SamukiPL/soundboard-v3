package me.samuki.core.storage.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.samuki.core.storage.Database
import me.samuki.core.storage.entity.CompilationEntityQueries
import me.samuki.core.storage.entity.PauseEntityQueries
import me.samuki.core.storage.entity.SoundEntityQueries

internal typealias CompilationQueries = CompilationEntityQueries
internal typealias SoundQueries = SoundEntityQueries
internal typealias PauseQueries = PauseEntityQueries

@Module
@InstallIn(SingletonComponent::class)
internal object QueriesModule {

    @Provides
    fun compilationQueries(database: Database): CompilationQueries =
        database.compilationEntityQueries

    @Provides
    fun soundQueries(database: Database): SoundQueries = database.soundEntityQueries

    @Provides
    fun pauseQueries(database: Database): PauseQueries = database.pauseEntityQueries
}
