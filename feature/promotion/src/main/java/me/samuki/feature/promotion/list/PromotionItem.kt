package me.samuki.feature.promotion.list

import androidx.compose.runtime.Stable
import me.samuki.model.values.ImageUrl
import me.samuki.model.values.Name
import me.samuki.model.values.StoreUrl

@Stable
internal data class PromotionItem(
    val fullName: Name,
    val image: ImageUrl,
    val name: Name,
    val url: StoreUrl,
)
