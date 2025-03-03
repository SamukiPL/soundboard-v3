package me.samuki.resource.promotion.model

import kotlinx.serialization.Serializable

@Serializable
internal data class LocalPromotion(
    val fullName: String,
    val image: String,
    val name: String,
    val url: String
)
