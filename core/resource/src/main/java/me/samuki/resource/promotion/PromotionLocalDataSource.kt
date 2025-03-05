package me.samuki.resource.promotion

import me.samuki.model.Promotion

public interface PromotionLocalDataSource {

    public suspend fun getPromotion(): List<Promotion>
}
