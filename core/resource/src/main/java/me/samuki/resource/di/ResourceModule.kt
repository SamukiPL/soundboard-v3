package me.samuki.resource.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.samuki.resource.provider.ResourceSoundsProvider
import me.samuki.resource.provider.SoundsProvider

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ResourceModule {
    @Binds
    abstract fun soundsProvider(resourceSoundsProvider: ResourceSoundsProvider): SoundsProvider
}
