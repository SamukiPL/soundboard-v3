package me.samuki.model

import kotlinx.serialization.Serializable
import me.samuki.model.values.Id
import me.samuki.model.values.Name

@Serializable
public data class Compilation(
    override val id: Id,
    override val name: Name,
    val sounds: List<Combinable>
) : Playable
