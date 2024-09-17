@file:OptIn(ExperimentalSharedTransitionApi::class)

package me.samuki.feature.compilation.presentation.bottom

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import androidx.compose.ui.util.fastFirstOrNull
import me.samuki.feature.compilation.presentation.CreatorContract
import kotlin.Unit
import kotlin.math.absoluteValue
import kotlin.math.pow

context(SharedTransitionScope, AnimatedVisibilityScope)
@Composable
internal fun CompilationCreatorPauseBar(
    onEvent: (CreatorContract.Event) -> Unit,
    changeBar: () -> Unit,
    modifier: Modifier = Modifier,
) = with(this@SharedTransitionScope) {
    val listState = rememberLazyListState()
    val snapFlingBehavior = rememberSnapFlingBehavior(listState)

    val itemsList by remember {
        mutableStateOf(
            (0..505 step 5).map { "${it.toBigDecimal().movePointLeft(2)}s" }
        )
    }

    val density = LocalDensity.current
    val width by remember {
        derivedStateOf {
            val tmpWidth = listState.layoutInfo.viewportSize.width / 3
            with(density) { tmpWidth.toDp() }
        }
    }


    Row(
        modifier = modifier
            .background(MaterialTheme.colorScheme.primary),
    ) {
        IconButton(
            onClick = {},
        ) {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "Accept",
                tint = MaterialTheme.colorScheme.onPrimary,
            )
        }
        LazyRow(
            modifier = Modifier.weight(1f),
            state = listState,
            flingBehavior = snapFlingBehavior,
            verticalAlignment = Alignment.CenterVertically
        ) {
            itemsIndexed(itemsList, key = { index, item -> item }) { index, item ->
                val alpha by remember {
                    derivedStateOf {
                        listState.layoutInfo
                            .visibleItemsInfo
                            .fastFirstOrNull { it.index == index }
                            ?.let { item ->
                                val centerOffset = (item.offset + item.size / 2f) / (item.size * 1.5f)
                                1f - (centerOffset - 1f).absoluteValue
                            } ?: 0f
                    }
                }
                val fontSize: Float by remember {
                    derivedStateOf { 10f + (8f * alpha) }
                }
                Box(
                    modifier = Modifier
                        .width(width)
                        .fillMaxHeight(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        item,
                        color = MaterialTheme.colorScheme.onPrimary.copy(
                            alpha = alpha
                        ),
                        fontSize = fontSize.sp
                    )
                }
            }
        }
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
                {},
                {},
                modifier = Modifier.height(48.dp)
            )
        }
    }
}
