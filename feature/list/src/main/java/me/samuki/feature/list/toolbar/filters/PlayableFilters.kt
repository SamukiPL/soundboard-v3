package me.samuki.feature.list.toolbar.filters

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import me.samuki.feature.list.ListContract
import me.samuki.feature.list.toolbar.ToolbarState
import me.samuki.model.values.Query

@Composable
internal fun PlayableFilters(
    state: ToolbarState,
    onEvent: (ListContract.Event) -> Unit,
    modifier: Modifier = Modifier
) {
    var iconWidth by remember {
        mutableStateOf(0.dp)
    }
    val density = LocalDensity.current

    Box(modifier = modifier) {
        LazyRow(
            verticalAlignment = Alignment.CenterVertically,
            contentPadding = PaddingValues(start = iconWidth, end = 4.dp),
            modifier = Modifier
        ) {
            items(state.filters, key = { Pair(it.id, it.order) }) {
                when (it) {
                    is QueryFilterItem -> QueryFilterView(
                        queryFilterItem = it,
                        onEvent = onEvent,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                    is ListTypeFilterItem -> ListTypeFilterView(
                        item = it,
                        onEvent = onEvent,
                        modifier = Modifier.padding(horizontal = 4.dp)
                    )
                }
            }
        }
        AnimatedVisibility(
            visible = state.showQueryButton,
            modifier = Modifier.onGloballyPositioned {
                iconWidth = with(density) {
                    it.size.width.toDp()
                }
            }) {
            IconButton(
                onClick = { onEvent(ListContract.Event.OpenQueryToolbar(Query.Empty)) },
                modifier = Modifier
                    .fillMaxHeight()
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}
