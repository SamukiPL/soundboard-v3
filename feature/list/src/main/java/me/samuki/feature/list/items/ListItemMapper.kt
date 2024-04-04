package me.samuki.feature.list.items

import me.samuki.model.Compilation
import me.samuki.model.Playable
import me.samuki.model.Sound
import me.samuki.model.mapper.key

internal fun Playable.toListItem(): ListItem = when (this) {
    is Compilation -> toListItem()
    is Sound -> toListItem()
}

private fun Sound.toListItem(): SoundItem = SoundItem(
    key = key,
    name = name,
    likeState = likeState,
    sound = this
)

private fun Compilation.toListItem(): ListItem = CompilationItem(
    key = key,
    name = name,
    compilation = this
)
