package me.samuki.model.mapper

import me.samuki.model.Key
import me.samuki.model.Playable

public val Playable.key: Key
    get() = Key(
        id = id,
        supplement = supplement
    )
