package me.samuki.data.promotion

import me.samuki.domain.promotion.PromotionRepository
import me.samuki.domain.rail.ListResult
import me.samuki.model.Promotion
import me.samuki.network.endpoint.PromotionEndpoint
import me.samuki.resource.promotion.PromotionLocalDataSource
import javax.inject.Inject

internal class PromotionRepositoryImpl @Inject constructor(
    private val promotionEndpoint: PromotionEndpoint,
    private val promotionLocalDataSource: PromotionLocalDataSource,
) : PromotionRepository {

    override suspend fun getPromotions(): ListResult<Promotion> = runCatching {
        promotionEndpoint.getPromotions()
    }

    override suspend fun getOfflinePromotions(): ListResult<Promotion> = runCatching {
        promotionLocalDataSource.getPromotion()
    }
}
