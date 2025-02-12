package me.samuki.feature.list.fab

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.samuki.feature.list.ListContract
import me.samuki.feature.list.R

internal typealias FabOpenState = Boolean

@Composable
internal fun ListFabView(
    onEvent: (ListContract.Event) -> Unit,
    modifier: Modifier = Modifier
) {
    var isOpen by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        ListFabItem.entries.forEachIndexed { index, item ->
            ListFabMenuItemView(
                isOpen = isOpen,
                index = index,
                item = item,
                onEvent = onEvent
            )
        }
        FloatingActionButton(
            onClick = { isOpen = !isOpen },
            shape = FloatingActionButtonDefaults.largeShape,
            modifier = Modifier.padding(top = 4.dp)
        ) {
            AnimatedContent(
                targetState = isOpen,
                transitionSpec = {
                    scaleIn().togetherWith(scaleOut())
                },
            ) {
                Icon(
                    contentDescription = stringResource(R.string.list_fab_content_description),
                    imageVector = it.fabIcon,
                )
            }
        }
    }
}

private val FabOpenState.fabIcon: ImageVector
    get() = when (this) {
        true -> Icons.Filled.Close
        false -> Icons.Filled.Add
    }

@Composable
@Preview
private fun ListFabViewPreview() = Box(
    modifier = Modifier.size(200.dp)
) {
    ListFabView({}, modifier = Modifier.align(Alignment.BottomEnd))
}

