package me.samuki.feature.list.items

import androidx.compose.runtime.Stable
import me.samuki.model.Compilation
import me.samuki.model.Sound
import me.samuki.model.values.Id
import me.samuki.model.values.LikeState
import me.samuki.model.values.Name

internal sealed interface ListItem {
    val id: Id
}

@Stable
internal data class SoundItem(
    override val id: Id,
    val name: Name,
    val likeState: LikeState,
    val sound: Sound
) : ListItem

@Stable
internal data class CompilationItem(
    override val id: Id,
    val name: Name,
    val compilation: Compilation
) : ListItem
