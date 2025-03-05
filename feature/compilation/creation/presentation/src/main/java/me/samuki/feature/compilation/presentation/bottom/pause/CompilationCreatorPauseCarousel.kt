@file:OptIn(ExperimentalFoundationApi::class)

package me.samuki.feature.compilation.presentation.bottom.pause

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.fastFirstOrNull
import kotlin.math.absoluteValue

@Composable
internal fun PauseCarousel(
    pauseItems: List<PausePickerItem>,
    listState: LazyListState,
    modifier: Modifier = Modifier
) {
    val snapFlingBehavior = rememberSnapFlingBehavior(listState)

    val density = LocalDensity.current
    val width by remember {
        derivedStateOf {
            val tmpWidth = listState.layoutInfo.viewportSize.width / 3
            with(density) { tmpWidth.toDp() }
        }
    }

    LazyRow(
        modifier = modifier,
        state = listState,
        flingBehavior = snapFlingBehavior,
        verticalAlignment = Alignment.CenterVertically
    ) {
        itemsIndexed(pauseItems, key = { index, item -> item.repeats }) { index, item ->
            val alpha by remember {
                derivedStateOf {
                    listState.layoutInfo
                        .visibleItemsInfo
                        .fastFirstOrNull { it.index == index }
                        ?.let { item ->
                            val centerOffset =
                                (item.offset + item.size / 2f) / (item.size * 1.5f)
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
                    item.text,
                    color = MaterialTheme.colorScheme.onPrimary.copy(
                        alpha = alpha
                    ),
                    fontSize = fontSize.sp
                )
            }
        }
    }
}
