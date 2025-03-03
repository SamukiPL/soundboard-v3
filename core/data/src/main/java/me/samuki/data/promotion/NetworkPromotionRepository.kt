package me.samuki.data.promotion

import me.samuki.domain.promotion.PromotionRepository
import me.samuki.domain.rail.ListResult
import me.samuki.model.Promotion
import me.samuki.network.endpoint.PromotionEndpoint
import javax.inject.Inject

internal class NetworkPromotionRepository @Inject constructor(
    private val promotionEndpoint: PromotionEndpoint,
) : PromotionRepository {

    override suspend fun getPromotions(): ListResult<Promotion> = runCatching {
        promotionEndpoint.getPromotions()
    }
}
