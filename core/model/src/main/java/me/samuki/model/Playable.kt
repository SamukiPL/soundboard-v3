package me.samuki.model

import me.samuki.model.values.Id
import me.samuki.model.values.LikeState
import me.samuki.model.values.Name
import me.samuki.model.values.Path

sealed interface Playable {

    val name: Name
}
