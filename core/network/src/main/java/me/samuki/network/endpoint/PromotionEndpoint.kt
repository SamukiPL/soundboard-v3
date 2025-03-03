package me.samuki.network.endpoint

import me.samuki.model.Promotion

public interface PromotionEndpoint {
    public suspend fun getPromotions(): List<Promotion>
}
