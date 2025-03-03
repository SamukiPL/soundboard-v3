package me.samuki.domain.promotion

import me.samuki.domain.rail.ListResult
import me.samuki.model.Promotion
import javax.inject.Inject

public class GetPromotions @Inject constructor(
    private val promotionRepository: PromotionRepository
) {

    public suspend operator fun invoke(): ListResult<Promotion> =
        promotionRepository.getPromotions()
}
