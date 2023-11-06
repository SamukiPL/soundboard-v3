package me.samuki.feature.list.items

import androidx.compose.runtime.Stable
import me.samuki.model.Compilation
import me.samuki.model.Key
import me.samuki.model.Sound
import me.samuki.model.values.LikeState
import me.samuki.model.values.Name

internal sealed interface ListItem {
    val key: Key
}

@Stable
internal data class SoundItem(
    override val key: Key,
    val name: Name,
    val likeState: LikeState,
    val sound: Sound
) : ListItem

@Stable
internal data class CompilationItem(
    override val key: Key,
    val name: Name,
    val compilation: Compilation
) : ListItem
