package me.samuki.data.compilation.mapper

import me.samuki.core.storage.model.StoragePause
import me.samuki.core.storage.model.StorageSound
import me.samuki.model.Combinable
import me.samuki.model.Pause
import me.samuki.model.Sound

internal fun List<Combinable>.toStorage() = map {
    when (it) {
        is Pause -> it.toStorage()
        is Sound -> it.toStorage()
    }
}

private fun Pause.toStorage() = StoragePause(
    repeats = repeats.toLong()
)

private fun Sound.toStorage() = StorageSound(
    resourceId = id,
    supplement = supplement
)
