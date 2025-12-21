package me.samuki.feature.list.toolbar

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.samuki.feature.list.ListContract
import me.samuki.feature.list.toolbar.filters.PlayableFilters
import me.samuki.feature.list.toolbar.query.ToolbarQueryView

@Composable
internal fun ListToolbar(
    toolbarState: ToolbarState,
    onEvent: (ListContract.Event) -> Unit,
    modifier: Modifier = Modifier,
) = Box(
    modifier = modifier
        .height(48.dp)
        .clip(MaterialTheme.shapes.extraLarge)
        .background(MaterialTheme.colorScheme.inversePrimary)
) {
    AnimatedContent(
        targetState = toolbarState.type,
        label = "toolbar",
        modifier = Modifier
            .fillMaxSize()
    ) {
        when (it) {
            ToolbarType.Filters -> PlayableFilters(
                state = toolbarState,
                onEvent = onEvent
            )

            ToolbarType.QueryInput -> ToolbarQueryView(
                state = toolbarState,
                onEvent = onEvent
            )

        }
    }
}

@Preview
@Composable
private fun ListToolbarPreview() {
    val mutableState = remember {
        mutableStateOf(
            ToolbarState()
        )
    }

    ListToolbar(
        toolbarState = mutableState.value,
        onEvent = {
            when (it) {
                is ListContract.Event.OpenQueryToolbar -> {
                    mutableState.value = ToolbarState(
                        type = ToolbarType.QueryInput
                    )
                }

                ListContract.Event.RemoveQuery,
                is ListContract.Event.SetQuery -> {
                    mutableState.value = ToolbarState(
                        type = ToolbarType.Filters
                    )
                }

                else -> Unit
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .clip(MaterialTheme.shapes.extraLarge)
            .background(MaterialTheme.colorScheme.inversePrimary)
    )
}
