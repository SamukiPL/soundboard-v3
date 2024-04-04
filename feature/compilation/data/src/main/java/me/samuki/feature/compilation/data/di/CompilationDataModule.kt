package me.samuki.feature.compilation.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import me.samuki.feature.compilation.data.LocalCompilationCreationFeatureRepository
import me.samuki.feature.compilation.domain.CompilationCreationFeatureRepository

@Module
@InstallIn(ActivityRetainedComponent::class)
internal abstract class CompilationDataModule {

    @Binds
    abstract fun compilationCreationFeatureRepository(
        localCompilationCreationFeatureRepository: LocalCompilationCreationFeatureRepository
    ): CompilationCreationFeatureRepository
}
