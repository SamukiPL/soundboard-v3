@file:OptIn(ExperimentalSharedTransitionApi::class)

package me.samuki.feature.compilation.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import me.samuki.core.presentation.events.ObserveAsEvents
import me.samuki.feature.compilation.presentation.bottom.CompilationCreatorBottomBar
import me.samuki.feature.compilation.presentation.controls.CompilationCreatorControlsView
import me.samuki.feature.compilation.presentation.dialog.CompilationCreatorFinishDialog
import me.samuki.feature.compilation.presentation.items.creator.CompilationCreatorItemsRow
import me.samuki.feature.compilation.presentation.items.sounds.CompilationCreatorSoundsColumn
import me.samuki.feature.compilation.presentation.preview.PreviewCreatorContractStateProvider

@Composable
public fun CompilationCreatorScreen(navigation: CompilationCreatorNavigation) {
    val viewModel: CompilationCreatorViewModel = hiltViewModel()

    LaunchedEffect(Unit) { viewModel.onEvent(CreatorContract.Event.Init) }

    ObserveAsEvents(viewModel.eventsFlow) {
        when (it) {
            CreatorContract.Effect.GoBackToList -> navigation.goBack()
            CreatorContract.Effect.GoToSuccessScreen -> navigation.goToCreationSuccessScreen()
        }
    }

    BackHandler {
        viewModel.onEvent(CreatorContract.Event.GoBack)
    }

    val state = remember { viewModel.state }
    val onEvent by remember { mutableStateOf(viewModel::onEvent) }

    CompilationCreatorContent(state, onEvent)
}

@Composable
private fun CompilationCreatorContent(
    state: CreatorContract.State,
    onEvent: (CreatorContract.Event) -> Unit
) = Box {
    SharedTransitionLayout(modifier = Modifier.statusBarsPadding()) {
        AnimatedContent(
            targetState = state.showSetNameDialog,
            label = "Dialog animation"
        ) { showSetNameDialog ->
            if (!showSetNameDialog) {
                Column {
                    CompilationCreatorItemsRow(
                        itemsList = state.list,
                        onEvent = onEvent,
                    )
                    CompilationCreatorControlsView(
                        showCreateButton = state.showCreateButton,
                        volumeEnabled = state.volumeEnabled,
                        onEvent = onEvent,
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.primaryContainer)
                    )
                    CompilationCreatorSoundsColumn(
                        sounds = state.sounds,
                        onEvent = onEvent,
                        modifier = Modifier.weight(1f)
                    )
                    CompilationCreatorBottomBar(
                        state = state.bottomBarState,
                        onEvent = onEvent
                    )
                }
            } else {
                CompilationCreatorFinishDialog(
                    combinables = state.list,
                    state = state.finishDialogState,
                    onEvent = onEvent
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewCompilationCreatorContent(
    @PreviewParameter(PreviewCreatorContractStateProvider::class) state: CreatorContract.State
) {
    Surface { CompilationCreatorContent(state = state) {} }
}
