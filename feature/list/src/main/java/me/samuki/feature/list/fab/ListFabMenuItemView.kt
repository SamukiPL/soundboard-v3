package me.samuki.feature.list.fab

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExposurePlus1
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import me.samuki.feature.list.ListContract
import me.samuki.core.ui.R

@Composable
internal fun ListFabMenuItemView(
    isOpen: FabOpenState,
    index: Int,
    item: ListFabItem,
    onEvent: (ListContract.Event) -> Unit,
    modifier: Modifier = Modifier,
) {
    AnimatedVisibility(
        visible = isOpen,
        enter = scaleIn(
            animationSpec = tween(
                durationMillis = 200,
                delayMillis = 100 - (50 * index),
            )
        ),
        exit = scaleOut(
            animationSpec = tween(
                durationMillis = 200,
                delayMillis = 100 * index,
            )
        ),
        modifier = modifier,
    ) {
        SmallFloatingActionButton(
            shape = FloatingActionButtonDefaults.largeShape,
            onClick = { onEvent(item.event) },
        ) {
            when (val icon = item.fabIcon()) {
                is ImageVector -> Icon(
                    contentDescription = "TODO",
                    imageVector = icon,
                )
                is Painter -> Icon(
                    contentDescription = "TODO",
                    painter = icon
                )
            }
        }
    }
}

@Composable
private fun ListFabItem.fabIcon(): Any = when (this) {
    ListFabItem.AddCompilation -> Icons.Filled.ExposurePlus1
    ListFabItem.GetMoreSoundboards -> painterResource(R.drawable.alpaca_logo)
}

@Composable
@Preview
private fun ListFabMenuItemViewPreview() = ListFabMenuItemView(
    isOpen = true,
    index = 1,
    item = ListFabItem.AddCompilation,
    onEvent = {},
)
