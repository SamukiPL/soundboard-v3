package me.samuki.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.samuki.data.compilation.LocalCompilationRepository
import me.samuki.data.permissions.LocalPermissionsRepository
import me.samuki.data.play.LocalPlayRepository
import me.samuki.data.promotion.PromotionRepositoryImpl
import me.samuki.data.set.LocalSetCompilationRepository
import me.samuki.data.set.LocalSetSoundRepository
import me.samuki.data.share.LocalShareRepository
import me.samuki.data.sound.LocalSoundRepository
import me.samuki.domain.PlayRepository
import me.samuki.domain.ShareRepository
import me.samuki.domain.compilation.CompilationRepository
import me.samuki.domain.compilation.set.SetCompilationRepository
import me.samuki.domain.permissions.PermissionsRepository
import me.samuki.domain.promotion.PromotionRepository
import me.samuki.domain.sound.SoundRepository
import me.samuki.domain.sound.set.SetSoundRepository

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {
    @Binds
    abstract fun soundRepository(localSoundRepository: LocalSoundRepository): SoundRepository

    @Binds
    abstract fun compilationRepository(localCompilationRepository: LocalCompilationRepository): CompilationRepository

    @Binds
    abstract fun playRepository(localPlayRepository: LocalPlayRepository): PlayRepository

    @Binds
    abstract fun setSoundRepository(localSetSoundRepository: LocalSetSoundRepository): SetSoundRepository

    @Binds
    abstract fun setCompilationRepository(localSetCompilationRepository: LocalSetCompilationRepository): SetCompilationRepository

    @Binds
    abstract fun shareRepository(localShareRepository: LocalShareRepository): ShareRepository

    @Binds
    abstract fun permissionsRepository(localPermissionsRepository: LocalPermissionsRepository): PermissionsRepository

    @Binds
    abstract fun promotionRepository(networkPromotionRepository: PromotionRepositoryImpl): PromotionRepository
}
