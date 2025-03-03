package me.samuki.model

import me.samuki.model.values.ImageUrl
import me.samuki.model.values.SoundboardUrl
import me.samuki.model.values.Name

public data class Promotion(
    val fullName: Name,
    val image: ImageUrl,
    val name: Name,
    val url: SoundboardUrl,
)
