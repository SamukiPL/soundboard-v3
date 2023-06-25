package me.samuki.resource.model

import me.samuki.model.values.Id
import me.samuki.model.values.Name
import me.samuki.model.values.Path

data class ResourceSound(
    val id: Id,
    val name: Name,
    val path: Path
)
