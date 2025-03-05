package me.samuki.network.response

import kotlinx.serialization.Serializable

@Serializable
internal data class ApiPromotion(
    val fullName: String,
    val image: String,
    val name: String,
    val url: String
)
