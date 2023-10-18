package me.samuki.feature.list.toolbar.query

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import me.samuki.feature.list.ListContract
import me.samuki.feature.list.toolbar.AcceptQueryEnable
import me.samuki.feature.list.toolbar.ToolbarState
import me.samuki.model.values.Query
import me.samuki.model.values.getQueryValue

@Composable
internal fun QueryView(
    state: ToolbarState,
    onEvent: (ListContract.Event) -> Unit,
    modifier: Modifier = Modifier
) {
    val focusRequester = remember {
        FocusRequester()
    }

    Row(
        modifier = modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LeadingIcon(state.acceptQueryEnable, onEvent)
        Box(modifier = Modifier.weight(1f)) {
            BasicTextField(
                value = state.query.getQueryValue(),
                onValueChange = { input ->
                    onEvent(
                        ListContract.Event.SetQuery(
                            Query.Text(input)
                        )
                    )
                },
                textStyle = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterStart)
                    .focusRequester(focusRequester)
            )
            androidx.compose.animation.AnimatedVisibility(
                visible = state.showQueryPlaceholder,
                modifier = Modifier.align(
                    Alignment.CenterStart
                ),
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Text(text = "Search...")
            }
        }
        TrailingIcon(onEvent)
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

@Composable
private fun LeadingIcon(
    acceptQueryEnable: AcceptQueryEnable,
    onEvent: (ListContract.Event) -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = { onEvent(ListContract.Event.AcceptQuery) },
        enabled = acceptQueryEnable,
    ) {
        Icon(
            imageVector = Icons.Filled.Check,
            contentDescription = "Accept",
            modifier = modifier
                .clip(CircleShape)
        )
    }
}

@Composable
private fun TrailingIcon(
    onEvent: (ListContract.Event) -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(onClick = { onEvent(ListContract.Event.RemoveQuery) }) {
        Icon(
            imageVector = Icons.Filled.Clear,
            contentDescription = "Clear",
            modifier = modifier
                .clip(CircleShape)
        )
    }
}
