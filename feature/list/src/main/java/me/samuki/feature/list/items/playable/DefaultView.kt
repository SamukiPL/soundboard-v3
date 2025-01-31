package me.samuki.feature.list.items.playable

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import me.samuki.feature.list.ListContract
import me.samuki.feature.list.items.PlayableItem

@Composable
internal fun DefaultView(
    item: PlayableItem,
    onEvent: (ListContract.Event) -> Unit,
    openOptions: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .padding(horizontal = 8.dp, vertical = 4.dp)
            .shadow(1.dp)
            .background(MaterialTheme.colorScheme.surface)
            .clickable {
                onEvent(ListContract.Event.Play(item.playable))
            }
            .padding(vertical = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(8.dp)
                .size(28.dp)
        ) {
            item.likeable?.let { likeable ->
                LikeableView(
                    likeable = likeable,
                    onEvent = onEvent,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
        Text(
            text = item.name.value,
            style = MaterialTheme.typography.headlineSmall,
            modifier = modifier
                .animateContentSize()
                .weight(1f)
        )
        IconButton(onClick = { openOptions() }) {
            Icon(
                imageVector = Icons.Filled.MoreVert,
                contentDescription = "Open Options",
                tint = MaterialTheme.colorScheme.primary
            )
        }
    }
}
