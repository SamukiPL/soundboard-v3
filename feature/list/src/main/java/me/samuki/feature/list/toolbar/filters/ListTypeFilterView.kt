package me.samuki.feature.list.toolbar.filters

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import me.samuki.feature.list.ListContract

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ListTypeFilterView(
    item: ListTypeFilterItem,
    onEvent: (ListContract.Event) -> Unit,
    modifier: Modifier = Modifier
) {
    FilterChip(
        selected = item.selected.value,
        onClick = {
            onEvent(ListContract.Event.ChangeFilter(item.filter))
        },
        label = {
            Text(text = item.name.value)
        },
        modifier = modifier
    )
}
