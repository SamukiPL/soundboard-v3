@file:OptIn(ExperimentalSharedTransitionApi::class)

package me.samuki.feature.compilation.presentation.bottom

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.samuki.feature.compilation.presentation.CreatorContract
import me.samuki.feature.compilation.presentation.bottom.pause.CompilationCreatorPauseBar

@Composable
internal fun CompilationCreatorBottomBar(
    state: BottomBarState,
    onEvent: (CreatorContract.Event) -> Unit,
    modifier: Modifier = Modifier
) {
    var type by remember { mutableStateOf(BottomBarType.Pause) }
    val listState = rememberLazyListState()
    SharedTransitionLayout {
        AnimatedContent(
            targetState = type,
            label = "Bottom Bar",
            transitionSpec = {
                slideInHorizontally(
                    initialOffsetX = { if (type.isQueryBar()) it else -it }
                ).togetherWith(
                    slideOutHorizontally(
                        targetOffsetX = { if (type.isPauseBar()) it else -it }
                    )
                )
            },
            modifier = modifier,
        ) {
            when (it) {
                BottomBarType.Pause -> CompilationCreatorPauseBar(
                    pauseItems = state.pauseItems,
                    listState = listState,
                    onEvent = onEvent,
                    changeBar = { type = BottomBarType.Query }
                )
                BottomBarType.Query -> CompilationCreatorQueryView(
                    queryViewState = state.queryViewState,
                    onEvent = onEvent,
                    changeBar = { type = BottomBarType.Pause }
                )
            }
        }
    }
}

private fun BottomBarType.isPauseBar(): Boolean = when (this) {
    BottomBarType.Pause -> true
    BottomBarType.Query -> false
}

private fun BottomBarType.isQueryBar(): Boolean = when (this) {
    BottomBarType.Pause -> false
    BottomBarType.Query -> true
}

@Preview
@Composable
private fun CompilationCreatorBottomBarPreview() {
    CompilationCreatorBottomBar(
        state = BottomBarState(),
        onEvent = {}
    )
}
