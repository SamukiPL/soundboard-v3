package me.samuki.model

import me.samuki.model.values.Id
import me.samuki.model.values.Name

public data class Compilation(
    val id: Id,
    override val name: Name,
    val sounds: List<Combinable>
) : Playable
