package me.samuki.core.storage.mapper.combinable

import me.samuki.core.storage.entity.PauseEntity
import me.samuki.core.storage.entity.SoundEntity
import me.samuki.core.storage.model.SortableCombinable
import me.samuki.core.storage.model.StorageCombinableId
import me.samuki.core.storage.model.StoragePause
import me.samuki.core.storage.model.StorageSound
import me.samuki.model.values.Id

internal fun StorageSound.toSoundEntity(
    id: StorageCombinableId,
    compilationId: Id,
    nextCombinableId: StorageCombinableId?
): SoundEntity = SoundEntity(
    id = id,
    compilation_id = compilationId,
    next_combinable_id = nextCombinableId,
    resource_id = resourceId,
    supplement = supplement
)

internal fun SoundEntity.toSortable(): SortableCombinable = SortableCombinable(
    id = id,
    compilationId = compilation_id,
    nextCombinableId = next_combinable_id,
    storageCombinable = StorageSound(
        resourceId = resource_id,
        supplement = supplement
    )
)
