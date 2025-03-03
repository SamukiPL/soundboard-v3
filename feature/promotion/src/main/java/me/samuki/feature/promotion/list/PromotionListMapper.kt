package me.samuki.feature.promotion.list

import me.samuki.model.Promotion

internal fun Promotion.toItem() = PromotionItem(
    fullName = fullName,
    image = image,
    name = name,
    url = url
)
