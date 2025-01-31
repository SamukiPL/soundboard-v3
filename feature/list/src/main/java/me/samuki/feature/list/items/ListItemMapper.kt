package me.samuki.feature.list.items

import me.samuki.model.Likeable
import me.samuki.model.Playable
import me.samuki.model.mapper.key

internal fun Playable.toListItem(): ListItem = PlayableItem(
    key = key,
    name = name,
    playable = this,
    likeable = this as? Likeable,
)
