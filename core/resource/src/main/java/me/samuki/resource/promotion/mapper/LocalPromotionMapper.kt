package me.samuki.resource.promotion.mapper

import me.samuki.model.Promotion
import me.samuki.model.values.ImageUrl
import me.samuki.model.values.Name
import me.samuki.model.values.StoreUrl
import me.samuki.resource.promotion.model.LocalPromotion

internal fun LocalPromotion.toDomain() = Promotion(
    fullName = Name(fullName),
    image = ImageUrl(image),
    name = Name(name),
    url = StoreUrl(url),
)
