@file:OptIn(ExperimentalSharedTransitionApi::class)

package me.samuki.feature.compilation.presentation.bottom.pause

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.samuki.feature.compilation.presentation.CreatorContract
import kotlin.Unit

context(SharedTransitionScope, AnimatedVisibilityScope)
@Composable
internal fun CompilationCreatorPauseBar(
    pauseItems: List<PausePickerItem>,
    listState: LazyListState,
    onEvent: (CreatorContract.Event) -> Unit,
    changeBar: () -> Unit,
    modifier: Modifier = Modifier,
) = with(this@SharedTransitionScope) {
    Row(
        modifier = modifier
            .background(MaterialTheme.colorScheme.primary)
            .navigationBarsPadding()
            .height(48.dp),
    ) {
        IconButton(
            onClick = {
                val middleIndex = listState.firstVisibleItemIndex + 1
                onEvent(
                    CreatorContract.Event.AddPause(
                        pauseItems[middleIndex]
                    )
                )
            },
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Accept",
                tint = MaterialTheme.colorScheme.onPrimary,
            )
        }
        PauseCarousel(
            pauseItems = pauseItems,
            listState = listState,
            modifier = Modifier.weight(1f)
        )
        IconButton(
            onClick = changeBar,
            modifier = Modifier
                .sharedBounds(
                    sharedContentState = rememberSharedContentState(key = "left"),
                    animatedVisibilityScope = this@AnimatedVisibilityScope,
                )
                .padding(start = 8.dp)
                .clip(RoundedCornerShape(topStart = 100.dp, bottomStart = 100.dp))
                .background(MaterialTheme.colorScheme.inversePrimary),
        ) {
            Icon(
                imageVector = Icons.Filled.Search,
                contentDescription = "Accept",
            )
        }
    }
}

@Composable
@Preview
private fun CompilationCreatorPauseBarPreview() {
    SharedTransitionLayout {
        AnimatedVisibility(true) {
            CompilationCreatorPauseBar(
                (0..10).map {
                    PausePickerItem(it, "$it s")
                },
                rememberLazyListState(),
                {},
                {},
                modifier = Modifier.height(48.dp)
            )
        }
    }
}
