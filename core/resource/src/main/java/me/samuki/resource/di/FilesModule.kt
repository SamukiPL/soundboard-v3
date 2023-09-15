package me.samuki.resource.di

import android.content.ContentResolver
import android.content.Context
import androidx.core.net.toUri
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.samuki.model.values.PackageName
import me.samuki.model.values.Path

@Module
@InstallIn(SingletonComponent::class)
internal object FilesModule {

    @Provides
    @DefaultName
    fun defaultName(@ApplicationContext context: Context): String =
        context.packageName.split('.').last()

    @Provides
    @SilencePath
    fun silencePath(packageName: PackageName): Path =
        Path("android.resource://${packageName.value}/raw/silence".toUri())

    @Provides
    fun contentResolver(@ApplicationContext context: Context): ContentResolver =
        context.contentResolver
}
