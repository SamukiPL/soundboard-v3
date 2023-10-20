package me.samuki.rationale

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import me.samuki.domain.params.SetType
import me.samuki.model.Playable

@Composable
public fun RationaleScreen(
    playable: Playable,
    setType: SetType,
    rationaleNavigation: RationaleNavigation
) {
    val viewModel: RationaleViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        viewModel.onEvent(RationaleContract.Event.SetupScreen(playable, setType))

        viewModel.eventsFlow.collect {
            when (it) {
                RationaleContract.Effect.GoBackToList -> rationaleNavigation.backToList()
            }
        }
    }

    val state = viewModel.state.collectAsState().value

    RationaleContent(
        state,
    ) { viewModel.onEvent(it) }
}

@Composable
private fun RationaleContent(
    @Suppress("UNUSED_PARAMETER") state: RationaleContract.State,
    @Suppress("UNUSED_PARAMETER") onEvent: (RationaleContract.Event) -> Unit
) {

}
