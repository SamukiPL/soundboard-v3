package me.samuki.feature.list.toolbar.filters

import androidx.compose.foundation.clickable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import me.samuki.feature.list.ListContract

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun QueryFilterView(
    queryFilterItem: QueryFilterItem,
    onEvent: (ListContract.Event) -> Unit,
    modifier: Modifier = Modifier
) {
    FilterChip(
        selected = true,
        onClick = {
            onEvent(ListContract.Event.OpenQueryToolbar(queryFilterItem.query))
        },
        label = {
            Text(queryFilterItem.name.value)
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = "Close",
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable { onEvent(ListContract.Event.RemoveQuery) }
            )
        },
        modifier = modifier
    )
}
