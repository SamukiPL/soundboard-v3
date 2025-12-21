package me.samuki.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.samuki.data.thankyou.ThankYouRepositoryImpl
import me.samuki.domain.thankyou.ThankYouRepository

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ThankYouModule {

    @Binds
    abstract fun bindThankYouRepository(impl: ThankYouRepositoryImpl): ThankYouRepository
}
