package me.samuki.feature.list.items.playable

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import me.samuki.feature.list.ListContract
import me.samuki.model.Likeable
import me.samuki.model.values.LikeState

@Composable
internal fun LikeableView(
    likeable: Likeable,
    onEvent: (ListContract.Event) -> Unit,
    modifier: Modifier = Modifier
) = with(likeable) {
    Crossfade(
        targetState = likeState,
        label = "Favourite Animation",
        modifier = modifier
    ) {
        IconButton(
            onClick = {
                onEvent(ListContract.Event.ChangeFavouriteState(this))
            },
            modifier = Modifier
                .clip(CircleShape)
        ) {
            Icon(
                imageVector = it.getIcon(),
                contentDescription = "Favourite",
                tint = it.getIconColor(),
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
