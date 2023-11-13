package me.samuki.model

import kotlinx.serialization.Serializable
import me.samuki.model.values.Id
import me.samuki.model.values.Supplement

@Serializable
public data class Key(
    public val id: Id,
    public val supplement: Supplement
)
