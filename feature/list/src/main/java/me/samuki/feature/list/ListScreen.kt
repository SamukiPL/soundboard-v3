package me.samuki.feature.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import me.samuki.core.presentation.events.ObserveAsEvents
import me.samuki.feature.list.fab.ListFabView
import me.samuki.feature.list.items.PlayableItem
import me.samuki.feature.list.items.playable.PlayableView
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
            ListContract.Effect.GoToMoreSoundboards -> navigation.goToMoreSoundboards()
        }
    }

    val state = remember { viewModel.state }
    val onEvent by remember { mutableStateOf(viewModel::onEvent) }

    ListContent(
        state,
        onEvent
    )
}

@Composable
private fun ListContent(
    state: ListContract.State,
    onEvent: (ListContract.Event) -> Unit
) = Box {
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .align(Alignment.Center)
    ) {
        ListToolbar(
            toolbarState = state.toolbarState,
            onEvent = onEvent,
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 4.dp)
                .fillMaxWidth()
        )
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 72.dp)
        ) {
            items(state.items, key = { it.key.toString() }) { item ->
                when (item) {
                    is PlayableItem -> PlayableView(
                        playableItem = item,
                        onEvent = onEvent,
                        modifier = Modifier.animateItem()
                    )
                }
            }
        }
    }
    ListFabView(
        onEvent = onEvent,
        modifier = Modifier.align(Alignment.BottomEnd)
            .padding(16.dp)
    )
}
