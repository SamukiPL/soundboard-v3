package me.samuki.domain.promotion

import me.samuki.domain.rail.ListResult
import me.samuki.model.Promotion

public interface PromotionRepository {

    public suspend fun getPromotions(): ListResult<Promotion>
}
