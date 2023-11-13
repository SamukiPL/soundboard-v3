package me.samuki.core.storage.mapper

import me.samuki.core.storage.entity.CompilationEntity
import me.samuki.core.storage.model.StorageCombinable
import me.samuki.core.storage.model.StorageCompilation

internal fun CompilationEntity.toStorage(combinables: List<StorageCombinable>): StorageCompilation =
    StorageCompilation(
        id = id,
        name = name,
        combinables = combinables
    )
