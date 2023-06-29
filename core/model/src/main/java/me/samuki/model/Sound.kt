package me.samuki.model

import me.samuki.model.values.Id
import me.samuki.model.values.LikeState
import me.samuki.model.values.Name
import me.samuki.model.values.Path

data class Sound(
    val id: Id,
    override val name: Name,
    val path: Path,
    val likeState: LikeState
) : Playable, Combinable
