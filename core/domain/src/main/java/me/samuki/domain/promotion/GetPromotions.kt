package me.samuki.domain.promotion

import me.samuki.model.PromotionResult
import me.samuki.model.values.DataOrigin
import javax.inject.Inject

public class GetPromotions @Inject constructor(
    private val promotionRepository: PromotionRepository
) {

    public suspend operator fun invoke(): Result<PromotionResult> =
        promotionRepository.getPromotions()
            .map {
                PromotionResult(
                    type = DataOrigin.ONLINE,
                    list = it,
                )
            }
            .recoverCatching {
                PromotionResult(
                    type = DataOrigin.OFFLINE,
                    list = promotionRepository.getOfflinePromotions().getOrThrow(),
                )
            }
}
