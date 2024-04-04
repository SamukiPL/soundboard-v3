package me.samuki.feature.compilation.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import me.samuki.core.presentation.events.ObserveAsEvents

@Composable
public fun CompilationCreatorScreen(navigation: CompilationCreatorNavigation) {
    val viewModel: CompilationCreatorViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        viewModel.onEvent(CreatorContract.Event.Init)
    }

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
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        items(state.list, key = { it.id.value }) {
            Text(
                text = "TEST TEST TEST TEST TEST",
                modifier = Modifier.clickable { onEvent(CreatorContract.Event.EndCreation) })
        }
    }
}
