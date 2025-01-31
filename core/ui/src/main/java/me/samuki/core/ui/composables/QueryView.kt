package me.samuki.feature.list.toolbar

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.samuki.core.ui.composables.AcceptQueryEnable
import me.samuki.core.ui.composables.QueryViewState
import me.samuki.model.values.Query
import me.samuki.model.values.getQueryValue

private fun Modifier.nullableFocusRequester(focusRequester: FocusRequester?): Modifier {
    return this then if (focusRequester != null)
        Modifier.focusRequester(focusRequester)
    else
        Modifier
}

@Composable
public fun QueryView(
    state: QueryViewState,
    onQueryChange: (Query.Text) -> Unit,
    modifier: Modifier = Modifier,
    acceptQuery: (() -> Unit)? = null,
    removeQuery: (() -> Unit)? = null,
    focusRequester: FocusRequester? = null,
): Unit = with(state) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        acceptQuery?.run { LeadingIcon(acceptQueryEnable, acceptQuery) }
        Box(modifier = Modifier.weight(1f)) {
            BasicTextField(
                value = query.getQueryValue(),
                onValueChange = { input ->
                    onQueryChange(
                        Query.Text(input)
                    )
                },
                textStyle = MaterialTheme.typography.titleMedium,
                maxLines = 1,
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterStart)
                    .nullableFocusRequester(focusRequester)
            )
            androidx.compose.animation.AnimatedVisibility(
                visible = showQueryPlaceholder,
                modifier = Modifier.align(
                    Alignment.CenterStart
                ),
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Text(text = "Search...")
            }
        }
        removeQuery?.run { TrailingIcon(removeQuery) }
    }
}

@Composable
private fun LeadingIcon(
    acceptQueryEnable: AcceptQueryEnable,
    acceptQuery: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = acceptQuery,
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
    removeQuery: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(onClick = removeQuery) {
        Icon(
            imageVector = Icons.Filled.Clear,
            contentDescription = "Clear",
            modifier = modifier
                .clip(CircleShape)
        )
    }
}

@Preview
@Composable
private fun QueryViewPreview() {
    QueryView(
        state = QueryViewState(),
        onQueryChange = {},
        acceptQuery = {},
        removeQuery = {},
        modifier = Modifier
            .height(48.dp)
            .background(MaterialTheme.colorScheme.inversePrimary),
        focusRequester = null,
    )
}
