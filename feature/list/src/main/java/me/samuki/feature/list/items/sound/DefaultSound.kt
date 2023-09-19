package me.samuki.feature.list.items.sound

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import me.samuki.feature.list.ListContract
import me.samuki.feature.list.items.SoundItem
import me.samuki.model.values.LikeState

@Composable
internal fun DefaultSound(
    soundItem: SoundItem,
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
                onEvent(ListContract.Event.Play(soundItem.sound))
            }
            .padding(vertical = 4.dp)
    ) {
        Crossfade(
            targetState = soundItem.likeState,
            label = "Favourite Animation",
            modifier = Modifier
        ) {
            IconButton(
                onClick = {
                    onEvent(ListContract.Event.ChangeFavouriteState(soundItem.sound))
                },
                modifier = Modifier
                    .clip(CircleShape)
            ) {
                Icon(
                    imageVector = it.getIcon(),
                    contentDescription = "Favourite",
                    tint = it.getIconColor(),
                    modifier = Modifier
                        .size(28.dp)
                )
            }
        }
        Text(
            text = soundItem.name.value,
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

private fun LikeState.getIcon(): ImageVector = when (this) {
    LikeState.Favourite -> Icons.Filled.Favorite
    LikeState.Normal -> Icons.Filled.FavoriteBorder
}

@Composable
private fun LikeState.getIconColor(): Color = when (this) {
    LikeState.Favourite -> MaterialTheme.colorScheme.primary
    LikeState.Normal -> MaterialTheme.colorScheme.onSurface
}
