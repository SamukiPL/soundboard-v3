@file:OptIn(ExperimentalSharedTransitionApi::class, ExperimentalMaterial3Api::class)

package me.samuki.feature.compilation.presentation.dialog

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import me.samuki.core.ui.composables.buttons.CircleIconButton
import me.samuki.feature.compilation.presentation.CreatorContract
import me.samuki.feature.compilation.presentation.items.creator.CompilationCreatorItem

context(SharedTransitionScope, AnimatedVisibilityScope)
@Composable
internal fun CompilationCreatorFinishDialog(
    combinables: List<CompilationCreatorItem>,
    state: FinishDialogState,
    onEvent: (CreatorContract.Event) -> Unit,
) {
    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .sharedBounds(
                sharedContentState = rememberSharedContentState(key = "Dialog Bounds"),
                animatedVisibilityScope = this@AnimatedVisibilityScope,
            )
            .animateEnterExit()
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Last step",
                    color = MaterialTheme.colorScheme.onBackground
                )
            },
            navigationIcon = {
                CircleIconButton(
                    onClick = {
                        onEvent(CreatorContract.Event.GoBack)
                    },
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "Favourite",
                    background = MaterialTheme.colorScheme.surfaceContainerHighest,
                    tint = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .padding(8.dp)
                )
            }
        )
        CompilationCreatorNameField(
            state.name,
            state.showNamePlaceholder,
            onEvent,
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 2.dp)
        )
        CompilationCreationFinalList(
            items = combinables,
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 2.dp)
                .fillMaxWidth()
                .weight(1f)
        )
        CompilationCreationFinishButton(
            finishCreationEnabled = state.finishCreationEnabled,
            onEvent = onEvent,
            modifier = Modifier
                .padding(horizontal = 8.dp, vertical = 2.dp)
                .fillMaxWidth(),
        )
    }
}

@Preview
@Composable
private fun DialogPreview() {
    SharedTransitionLayout {
        AnimatedVisibility(true) {
            CompilationCreatorFinishDialog(
                emptyList(),
                FinishDialogState()
            ) { }
        }
    }
}
