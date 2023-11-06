package me.samuki.core.storage.model

import me.samuki.model.values.Id

internal data class SortableCombinable(
    val id: StorageCombinableId,
    val compilationId: Id,
    val nextCombinableId: StorageCombinableId?,
    val storageCombinable: StorageCombinable
)
