package me.samuki.feature.list

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import me.samuki.core.presentation.events.ObserveAsEvents
import me.samuki.feature.list.items.CompilationItem
import me.samuki.feature.list.items.CompilationView
import me.samuki.feature.list.items.SoundItem
import me.samuki.feature.list.items.sound.SoundView
import me.samuki.feature.list.toolbar.ListToolbar

@Composable
public fun ListScreen(
    navigation: ListNavigation
) {
    val viewModel: ListViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        viewModel.onEvent(ListContract.Event.Init)
    }

    ObserveAsEvents(viewModel.eventsFlow) {
        when (it) {
            is ListContract.Effect.GoToSettingsRationale -> navigation.goToSettingsRationale(
                it.playable,
                it.setType
            )
            ListContract.Effect.GoToCompilationCreation -> navigation.goToCompilationCreation()
        }
    }

    navigation.goToCompilationCreation()
    val state = remember { viewModel.state }
    val onEvent by remember { mutableStateOf(viewModel::onEvent) }

    ListContent(
        state,
        onEvent
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ListContent(
    state: ListContract.State,
    onEvent: (ListContract.Event) -> Unit
) {
    Column {
        ListToolbar(
            toolbarState = state.toolbarState,
            onEvent = onEvent,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 4.dp)
                .fillMaxWidth()
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(state.items, key = { it.key.toString() }) { item ->
                when (item) {
                    is CompilationItem -> CompilationView(/*compilationItem = item, onEvent = onEvent*/)
                    is SoundItem -> SoundView(
                        soundItem = item,
                        onEvent = onEvent,
                        modifier = Modifier.animateItem()
                    )
                }
            }
        }
    }
}
