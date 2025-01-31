package me.samuki.model

import kotlinx.serialization.Serializable
import me.samuki.model.values.LikeState

@Serializable
public sealed interface Likeable {
    public val likeState: LikeState
}
