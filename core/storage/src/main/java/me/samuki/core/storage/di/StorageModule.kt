package me.samuki.core.storage.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.samuki.core.storage.CompilationsDataSource
import me.samuki.core.storage.StorageCompilationsDataSource

@Module
@InstallIn(SingletonComponent::class)
internal abstract class StorageModule {

    @Binds
    abstract fun compilationsDataSource(storageCompilationsDataSource: StorageCompilationsDataSource): CompilationsDataSource
}
