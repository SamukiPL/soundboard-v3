package me.samuki.feature.compilation.presentation.items.creator

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import me.samuki.feature.compilation.presentation.CreatorContract

@Composable
internal fun CompilationCreatorItemsRow(
    itemsList: List<CompilationCreatorItem>,
    onEvent: (CreatorContract.Event) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier =
        Modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .fillMaxWidth()
            .height(48.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(itemsList, key = { it.id.toString() }) {
            CompilationCreatorItemView(
                combinedItem = it,
                onEvent = onEvent,
            )
        }
    }
}
