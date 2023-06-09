package me.samuki.resource.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.samuki.resource.player.AndroidMediaPlayer
import me.samuki.resource.player.Player
import me.samuki.resource.sounds.favourite.PreferencesFavouriteProvider
import me.samuki.resource.sounds.favourite.FavouriteProvider
import me.samuki.resource.sounds.provider.ResourceSoundsDataSource
import me.samuki.resource.sounds.provider.SoundsDataSource

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ResourceModule {

    @Binds
    abstract fun favouriteProvider(preferencesFavouriteProvider: PreferencesFavouriteProvider): FavouriteProvider

    @Binds
    abstract fun soundsProvider(resourceSoundsDataSource: ResourceSoundsDataSource): SoundsDataSource

    @Binds
    abstract fun player(androidMediaPlayer: AndroidMediaPlayer): Player
}
