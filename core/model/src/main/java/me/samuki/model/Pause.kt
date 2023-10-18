package me.samuki.model

import kotlinx.serialization.Serializable

@Serializable
@JvmInline
public value class Pause(
    public val repeats: Int
) : Combinable
