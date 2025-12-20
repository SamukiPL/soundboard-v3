package me.samuki.network.di.endpoint

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import me.samuki.model.Promotion
import me.samuki.network.endpoint.PromotionEndpoint
import me.samuki.network.mappers.toDomain
import me.samuki.network.response.ApiPromotion
import javax.inject.Inject

internal class NetworkPromotionEndpoint @Inject constructor(
    private val client: HttpClient,
) : PromotionEndpoint {

    override suspend fun getPromotions(): List<Promotion> = client.get(PROMOTION_URL)
        .body<List<ApiPromotion>>()
        .map { it.toDomain() }

    private companion object {
        private const val PROMOTION_URL =
            "https://samukipl.github.io/alpaka-studio-promotion/soundboard/promotion.json"
    }
}
