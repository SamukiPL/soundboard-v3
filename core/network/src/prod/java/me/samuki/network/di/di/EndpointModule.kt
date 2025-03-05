package me.samuki.network.di.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import me.samuki.network.di.endpoint.NetworkPromotionEndpoint
import me.samuki.network.endpoint.PromotionEndpoint

@Module
@InstallIn(SingletonComponent::class)
internal abstract class EndpointModule {

    @Binds
    abstract fun promotionEndpoint(
        networkPromotionEndpoint: NetworkPromotionEndpoint,
    ): PromotionEndpoint
}
