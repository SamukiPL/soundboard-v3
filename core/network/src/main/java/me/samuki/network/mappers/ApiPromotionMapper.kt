package me.samuki.network.mappers

import me.samuki.model.Promotion
import me.samuki.model.values.ImageUrl
import me.samuki.model.values.Name
import me.samuki.model.values.SoundboardUrl
import me.samuki.network.response.ApiPromotion

internal fun ApiPromotion.toDomain() = Promotion(
    fullName = Name(fullName),
    image = ImageUrl(image),
    name = Name(name),
    url = SoundboardUrl(url),
)
