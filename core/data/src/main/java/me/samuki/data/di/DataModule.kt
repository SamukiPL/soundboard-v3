package me.samuki.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import me.samuki.data.sound.LocalSoundRepository
import me.samuki.domain.sound.SoundRepository

@Module
@InstallIn(ActivityRetainedComponent::class)
internal abstract class DataModule {
    @Binds
    abstract fun soundRepository(localSoundRepository: LocalSoundRepository): SoundRepository
}