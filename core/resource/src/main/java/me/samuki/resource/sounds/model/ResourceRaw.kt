package me.samuki.resource.sounds.model

import me.samuki.model.values.Id
import me.samuki.model.values.Name
import me.samuki.model.values.PackageName
import me.samuki.model.values.Path

internal data class ResourceRaw(
    val id: Id,
    val packageName: PackageName,
    val name: Name,
    val path: Path
)
