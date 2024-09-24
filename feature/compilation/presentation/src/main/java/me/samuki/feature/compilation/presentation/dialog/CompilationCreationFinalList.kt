package me.samuki.feature.compilation.presentation.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.samuki.feature.compilation.presentation.items.creator.CompilationCreatorItem
import me.samuki.feature.compilation.presentation.preview.PreviewCompilationCreatorItemProvider
import me.samuki.model.Pause
import me.samuki.model.Sound

@Composable
internal fun CompilationCreationFinalList(
    items: List<CompilationCreatorItem>,
    modifier: Modifier = Modifier,
) {
    Card(modifier = modifier) {
        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            itemsIndexed(
                items,
                key = { index, item -> item.id.toString() }) { index, item ->
                when (val combinable = item.combinedCombinable.combinable) {
                    is Pause -> {
                        Box(
                            modifier = Modifier
                                .wrapContentSize()
                                .clip(CircleShape)
                                .background(MaterialTheme.colorScheme.primary)
                                .padding(8.dp),
                        ) {
                            Text(
                                text = "${
                                    combinable.repeats.toBigDecimal().movePointLeft(1)
                                }s",
                                color = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier.align(Alignment.Center),
                            )
                        }
                    }
                    is Sound -> {
                        Text(
                            text = combinable.name.value,
                            modifier = Modifier
                                .border(
                                    width = 1.dp,
                                    color = MaterialTheme.colorScheme.primary,
                                    shape = CircleShape
                                )
                                .padding(8.dp),
                        )
                    }
                }

                if (items.lastIndex != index) {
                    Box(
                        modifier = Modifier
                            .padding(4.dp)
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun CompilationCreationFinalListPreview() {
    Surface {
        CompilationCreationFinalList(
            PreviewCompilationCreatorItemProvider().values.toList(),
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 2.dp)
        )
    }
}
