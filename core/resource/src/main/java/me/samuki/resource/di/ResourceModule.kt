package me.samuki.resource.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.samuki.resource.player.AndroidMediaPlayer
import me.samuki.resource.player.Player
import me.samuki.resource.player.pause.PauseDelayer
import me.samuki.resource.player.pause.PauseDelayerImpl
import me.samuki.resource.promotion.PromotionLocalDataSource
import me.samuki.resource.promotion.ResourcePromotionDataSource
import me.samuki.resource.set.CompilationSetter
import me.samuki.resource.set.CompilationSettingsSetter
import me.samuki.resource.set.SoundSetter
import me.samuki.resource.set.SoundSettingsSetter
import me.samuki.resource.set.premissions.WriteSettingsPermissionChecker
import me.samuki.resource.set.premissions.WriteSettingsPermissionCheckerImpl
import me.samuki.resource.share.AndroidShareHub
import me.samuki.resource.share.ShareHub
import me.samuki.resource.sounds.favourite.FavouriteProvider
import me.samuki.resource.sounds.favourite.PreferencesFavouriteProvider
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

    @Binds
    abstract fun pauseDelayer(pauseDelayer: PauseDelayerImpl): PauseDelayer

    @Binds
    abstract fun soundSettingsSetter(soundSettingsSetter: SoundSettingsSetter): SoundSetter

    @Binds
    abstract fun compilationSettingsSetter(compilationSettingsSetter: CompilationSettingsSetter): CompilationSetter

    @Binds
    abstract fun shareHub(androidShareHub: AndroidShareHub): ShareHub

    @Binds
    abstract fun writeSettingsPermissionChecker(writeSettingsPermissionCheckerImpl: WriteSettingsPermissionCheckerImpl): WriteSettingsPermissionChecker

    @Binds
    abstract fun promotionDataSource(resourcePromotionDataSource: ResourcePromotionDataSource): PromotionLocalDataSource
}
