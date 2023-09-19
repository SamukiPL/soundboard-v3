package me.samuki.model.values

import kotlinx.serialization.Serializable

@Serializable
@JvmInline
public value class Id(
    public val value: Int
)
