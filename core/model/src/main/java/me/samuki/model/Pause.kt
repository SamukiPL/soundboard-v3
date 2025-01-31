package me.samuki.model

import kotlinx.serialization.Serializable

@Serializable
public data class Pause(
    public val repeats: Int
) : Combinable
