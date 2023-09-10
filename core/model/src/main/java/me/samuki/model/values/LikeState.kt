package me.samuki.model.values

public sealed interface LikeState {

    public data object Favourite : LikeState

    public data object Normal : LikeState
}
