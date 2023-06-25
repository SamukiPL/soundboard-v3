package me.samuki.soundboard.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.samuki.model.values.PackageName

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun packageName(@ApplicationContext context: Context): PackageName =
        PackageName(context.packageName)
}
