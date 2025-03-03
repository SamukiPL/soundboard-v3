package me.samuki.model

import me.samuki.model.values.DataOrigin
import me.samuki.model.values.ImageUrl
import me.samuki.model.values.StoreUrl
import me.samuki.model.values.Name

public data class PromotionResult(
    val type: DataOrigin,
    val list: List<Promotion>,
)

public data class Promotion(
    val fullName: Name,
    val image: ImageUrl,
    val name: Name,
    val url: StoreUrl,
)
