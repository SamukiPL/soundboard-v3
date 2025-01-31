package me.samuki.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import me.samuki.data.filters.LocalFiltersRepository
import me.samuki.data.search.LocalSearchRepository
import me.samuki.domain.filters.FiltersRepository
import me.samuki.domain.search.SearchRepository

@Module
@InstallIn(ViewModelComponent::class)
internal abstract class DataPerScreenModule {

    @Binds
    abstract fun searchRepository(localSearchRepository: LocalSearchRepository): SearchRepository

    @Binds
    abstract fun filtersRepository(localFiltersRepository: LocalFiltersRepository): FiltersRepository
}
