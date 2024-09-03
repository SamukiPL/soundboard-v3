package me.samuki.feature.compilation.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import me.samuki.core.presentation.events.ObserveAsEvents
import me.samuki.feature.compilation.presentation.controls.CompilationCreatorControlsView
import me.samuki.feature.compilation.presentation.items.creator.CompilationCreatorItemView
import me.samuki.feature.compilation.presentation.items.sounds.CompilationCreatorSoundView
import me.samuki.feature.compilation.presentation.preview.PreviewCreatorContractStateProvider
import me.samuki.feature.compilation.presentation.queryField.CompilationCreatorQueryView

@Composable
public fun CompilationCreatorScreen(navigation: CompilationCreatorNavigation) {
    val viewModel: CompilationCreatorViewModel = hiltViewModel()

    LaunchedEffect(Unit) { viewModel.onEvent(CreatorContract.Event.Init) }

    ObserveAsEvents(viewModel.eventsFlow) {
        when (it) {
            CreatorContract.Effect.GoBackToList -> navigation.goBack()
        }
    }

    val state = viewModel.state.collectAsState().value

    CompilationCreatorContent(state) { viewModel.onEvent(it) }
}

@Composable
private fun CompilationCreatorContent(
    state: CreatorContract.State,
    onEvent: (CreatorContract.Event) -> Unit
) {
    Column {
        LazyRow(
            modifier =
                Modifier.padding(horizontal = 16.dp, vertical = 4.dp).fillMaxWidth().height(48.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.list, key = { it.id.toString() }) {
                CompilationCreatorItemView(
                    combinedItem = it,
                    onEvent = onEvent,
                )
            }
        }
        LazyColumn(
            modifier = Modifier.padding(horizontal = 8.dp).weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.sounds, key = { it.key.toString() }) {
                CompilationCreatorSoundView(compilationCreatorSound = it, onEvent = onEvent)
            }
        }
        CompilationCreatorControlsView(onEvent)
        CompilationCreatorQueryView()
    }
}

@Preview
@Composable
private fun PreviewCompilationCreatorContent(
    @PreviewParameter(PreviewCreatorContractStateProvider::class) state: CreatorContract.State
) {
    Surface { CompilationCreatorContent(state = state) {} }
}
