package me.samuki.model

import me.samuki.model.values.Id
import me.samuki.model.values.Name

sealed interface Playable {
    data class Sound(
        val id: Id,
        val isFavourite: Boolean
    ): Playable

    data class Compilation(
        val id: Id,
        val name: Name,
        val sounds: List<Sound>
    ): Playable
}
