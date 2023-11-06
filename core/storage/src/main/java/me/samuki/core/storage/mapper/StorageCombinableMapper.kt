package me.samuki.core.storage.mapper

import me.samuki.core.storage.entity.PauseEntity
import me.samuki.core.storage.entity.SoundEntity
import me.samuki.core.storage.mapper.combinable.toPauseEntity
import me.samuki.core.storage.mapper.combinable.toSoundEntity
import me.samuki.core.storage.model.EntitiesToSave
import me.samuki.core.storage.model.StorageCombinable
import me.samuki.core.storage.model.StorageCombinableId
import me.samuki.core.storage.model.StoragePause
import me.samuki.core.storage.model.StorageSound
import me.samuki.model.values.Id

internal fun List<StorageCombinable>.toEntities(compilationId: Id): EntitiesToSave {
    val combinableIds = (indices).map { StorageCombinableId() }
    return mapIndexed { index, combinable ->
        combinable.toEntity(
            combinableIds[index],
            compilationId,
            combinableIds.getOrNull(index + 1)
        )
    }.let { combinables ->
        EntitiesToSave(
            pauses = combinables.filterIsInstance(PauseEntity::class.java),
            sounds = combinables.filterIsInstance(SoundEntity::class.java)
        )
    }
}

private fun StorageCombinable.toEntity(
    id: StorageCombinableId,
    compilationId: Id,
    nextCombinableId: StorageCombinableId?
): Any = when (this) {
    is StoragePause -> toPauseEntity(
        id = id,
        compilationId = compilationId,
        nextCombinableId = nextCombinableId
    )
    is StorageSound -> toSoundEntity(
        id = id,
        compilationId = compilationId,
        nextCombinableId = nextCombinableId
    )
}
