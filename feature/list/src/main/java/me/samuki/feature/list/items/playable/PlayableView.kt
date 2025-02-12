package me.samuki.feature.list.items.playable

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import me.samuki.feature.list.ListContract
import me.samuki.feature.list.items.PlayableItem
import me.samuki.feature.list.items.options.OptionsChooser
import me.samuki.feature.list.items.options.OptionsState
import me.samuki.feature.list.previews.PreviewPlayableItemProvider
import me.samuki.model.util.EMPTY_STRING

@Composable
internal fun PlayableView(
    playableItem: PlayableItem,
    onEvent: (ListContract.Event) -> Unit,
    modifier: Modifier = Modifier
) {
    var optionsState by remember { mutableStateOf(OptionsState.Closed) }
    val density = LocalDensity.current
    var height by remember { mutableStateOf(0.dp) }
    AnimatedContent(
        targetState = optionsState,
        modifier = modifier
            .onGloballyPositioned {
                with(density) { height = it.size.height.toDp() }
            },
        transitionSpec = {
            slideInHorizontally {
                optionsState.openAnimation(it)
            }.togetherWith(slideOutHorizontally {
                optionsState.closeAnimation(it)
            })
        },
        label = EMPTY_STRING
    ) {
        when (it) {
            OptionsState.Closed -> DefaultView(
                playableItem,
                onEvent = onEvent,
                openOptions = { optionsState = OptionsState.Open },
            )
            OptionsState.Open -> OptionsChooser(
                playable = playableItem.playable,
                onEvent = onEvent,
                closeOptions = { optionsState = OptionsState.Closed },
                modifier = Modifier.height(height)
            )
        }
    }
}

private fun OptionsState.openAnimation(offset: Int): Int = when (this) {
    OptionsState.Open -> offset
    OptionsState.Closed -> -offset
}

private fun OptionsState.closeAnimation(offset: Int): Int = when (this) {
    OptionsState.Open -> -offset
    OptionsState.Closed -> offset
}


@Preview
@Composable
private fun PlayableViewPreview(
    @PreviewParameter(PreviewPlayableItemProvider::class) item: PlayableItem
) {
    PlayableView(
        playableItem = item,
        onEvent = {},
        modifier = Modifier.height(64.dp)
    )
}
