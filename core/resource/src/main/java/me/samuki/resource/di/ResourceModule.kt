package me.samuki.resource.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.samuki.resource.favourite.PreferencesFavouriteProvider
import me.samuki.resource.favourite.FavouriteProvider
import me.samuki.resource.provider.ResourceSoundsDataSource
import me.samuki.resource.provider.SoundsDataSource

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ResourceModule {

    @Binds
    abstract fun favouriteProvider(preferencesFavouriteProvider: PreferencesFavouriteProvider): FavouriteProvider

    @Binds
    abstract fun soundsProvider(resourceSoundsDataSource: ResourceSoundsDataSource): SoundsDataSource
}
