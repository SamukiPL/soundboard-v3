package me.samuki.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.samuki.model.filters.ListType
import kotlin.enums.EnumEntries

@Module
@InstallIn(SingletonComponent::class)
internal object DataSourcesModule {

    @Provides
    fun listTypes(): EnumEntries<ListType> = ListType.entries
}
