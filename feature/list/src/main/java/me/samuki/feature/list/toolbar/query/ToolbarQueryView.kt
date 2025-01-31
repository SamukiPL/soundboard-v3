package me.samuki.feature.list.toolbar.query

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.samuki.feature.list.ListContract
import me.samuki.feature.list.toolbar.QueryView
import me.samuki.feature.list.toolbar.ToolbarState
import me.samuki.feature.list.toolbar.ToolbarType

@Composable
internal fun ToolbarQueryView(
    state: ToolbarState,
    onEvent: (ListContract.Event) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusRequester = remember {
        FocusRequester()
    }

    QueryView(
        state = state.queryViewState,
        focusRequester = focusRequester,
        onQueryChange = { query ->
            onEvent(
                ListContract.Event.SetQuery(query)
            )
        },
        acceptQuery = {
            onEvent(
                ListContract.Event.AcceptQuery
            )
        },
        removeQuery = {
            onEvent(
                ListContract.Event.RemoveQuery
            )
        },
        modifier = modifier
    )

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

@Preview
@Composable
private fun QueryViewPreview() {
    ToolbarQueryView(
        state = ToolbarState(
            type = ToolbarType.QueryInput,
        ),
        onEvent = {},
        modifier = Modifier
            .height(48.dp)
            .clip(MaterialTheme.shapes.extraLarge)
            .background(MaterialTheme.colorScheme.inversePrimary)
    )
}
