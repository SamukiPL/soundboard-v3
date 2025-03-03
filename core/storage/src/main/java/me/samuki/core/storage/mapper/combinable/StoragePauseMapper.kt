package me.samuki.core.storage.mapper.combinable

import me.samuki.core.storage.entity.PauseEntity
import me.samuki.core.storage.entity.SoundEntity
import me.samuki.core.storage.model.SortableCombinable
import me.samuki.core.storage.model.StorageCombinableId
import me.samuki.core.storage.model.StoragePause
import me.samuki.core.storage.model.StorageSound
import me.samuki.model.values.Id

internal fun StoragePause.toPauseEntity(
    id: StorageCombinableId,
    compilationId: Id,
    nextCombinableId: StorageCombinableId?
): PauseEntity = PauseEntity(
    id = id,
    compilation_id = compilationId,
    next_combinable_id = nextCombinableId,
    repeats = repeats
)

internal fun PauseEntity.toSortable(): SortableCombinable = SortableCombinable(
    id = id,
    compilationId = compilation_id,
    nextCombinableId = next_combinable_id,
    storageCombinable = StoragePause(
        repeats = repeats
    )
)
