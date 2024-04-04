package me.samuki.model.values

import kotlinx.serialization.Serializable

@Serializable
@JvmInline
public value class Name(
    public val value: String
) {

    public fun isNotEmpty(): Boolean = value.isNotEmpty()

    public fun isEmpty(): Boolean = value.isEmpty()
}
