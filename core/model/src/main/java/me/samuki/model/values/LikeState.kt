package me.samuki.model.values

sealed interface LikeState {

    object Favourite : LikeState

    object Normal : LikeState
}
