package me.samuki.model

import me.samuki.model.values.Id
import me.samuki.model.values.LikeState
import me.samuki.model.values.Name
import me.samuki.model.values.Path

sealed interface Playable {
    data class Sound(
        val id: Id,
        val name: Name,
        val path: Path,
        val likeState: LikeState
    ): Playable

    data class Compilation(
        val id: Id,
        val name: Name,
        val sounds: List<Sound>
    ): Playable
}
