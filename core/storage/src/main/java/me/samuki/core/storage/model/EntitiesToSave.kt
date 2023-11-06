package me.samuki.core.storage.model

import me.samuki.core.storage.entity.PauseEntity
import me.samuki.core.storage.entity.SoundEntity

internal data class EntitiesToSave(
    val pauses: List<PauseEntity>,
    val sounds: List<SoundEntity>
)
