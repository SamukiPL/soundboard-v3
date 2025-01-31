package me.samuki.feature.list.toolbar

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import me.samuki.feature.list.ListContract
import me.samuki.feature.list.toolbar.filters.PlayableFilters
import me.samuki.feature.list.toolbar.query.ToolbarQueryView

@Composable
internal fun ListToolbar(
    toolbarState: ToolbarState,
    onEvent: (ListContract.Event) -> Unit,
    modifier: Modifier
) {
    AnimatedContent(
        targetState = toolbarState.type,
        label = "toolbar",
        modifier = modifier
            .height(48.dp)
            .clip(MaterialTheme.shapes.extraLarge)
            .background(MaterialTheme.colorScheme.inversePrimary)
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
