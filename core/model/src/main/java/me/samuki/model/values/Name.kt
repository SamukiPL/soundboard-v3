package me.samuki.model.values

import kotlinx.serialization.Serializable

@Serializable
@JvmInline
public value class Name(
    public val value: String
)
