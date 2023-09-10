package me.samuki.soundboard.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.samuki.model.values.AppName
import me.samuki.model.values.PackageName
import me.samuki.soundboard.R

@Module
@InstallIn(SingletonComponent::class)
internal object AppModule {

    @Provides
    fun packageName(@ApplicationContext context: Context): PackageName =
        PackageName(context.packageName)

    @Provides
    fun appName(@ApplicationContext context: Context): AppName =
        AppName(context.getString(R.string.app_name))
}
