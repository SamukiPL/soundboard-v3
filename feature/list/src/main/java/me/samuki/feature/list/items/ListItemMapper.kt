package me.samuki.feature.list.items

import me.samuki.model.Compilation
import me.samuki.model.Playable
import me.samuki.model.Sound

internal fun Playable.toListItem() : ListItem = when(this) {
    is Compilation -> toListItem()
    is Sound -> toListItem()
}

private fun Sound.toListItem(): SoundItem = SoundItem(
    id = id,
    name = name,
    likeState = likeState,
    sound = this
)

private fun Compilation.toListItem(): ListItem = CompilationItem(
    id = id,
    name = name,
    compilation = this
)
