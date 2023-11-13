package me.samuki.core.storage.model

import me.samuki.model.values.Id
import me.samuki.model.values.Name

public data class StorageCompilation(
    val id: Id,
    val name: Name,
    val combinables: List<StorageCombinable>
)
