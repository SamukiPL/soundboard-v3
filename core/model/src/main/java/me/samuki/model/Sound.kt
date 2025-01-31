package me.samuki.model

import kotlinx.serialization.Serializable
import me.samuki.model.values.Id
import me.samuki.model.values.LikeState
import me.samuki.model.values.Name
import me.samuki.model.values.Path
import me.samuki.model.values.Supplement

@Serializable
public data class Sound(
    override val id: Id,
    override val supplement: Supplement,
    override val name: Name,
    override val likeState: LikeState,
    val path: Path,
) : Playable, Combinable, Likeable
