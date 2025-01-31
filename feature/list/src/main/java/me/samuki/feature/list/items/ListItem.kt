package me.samuki.feature.list.items

import androidx.compose.runtime.Stable
import me.samuki.model.Key
import me.samuki.model.Likeable
import me.samuki.model.Playable
import me.samuki.model.values.Name

internal sealed interface ListItem {
    val key: Key
}

@Stable
internal data class PlayableItem(
    override val key: Key,
    val name: Name,
    val playable: Playable,
    val likeable: Likeable?,
) : ListItem
