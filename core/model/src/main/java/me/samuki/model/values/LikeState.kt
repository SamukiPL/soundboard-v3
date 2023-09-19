package me.samuki.model.values

import kotlinx.serialization.Serializable

@Serializable
public sealed interface LikeState {

    @Serializable
    public data object Favourite : LikeState

    @Serializable
    public data object Normal : LikeState
}
