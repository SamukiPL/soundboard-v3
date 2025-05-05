package me.samuki.rationale

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import me.samuki.domain.params.SetType
import me.samuki.model.Playable
import me.samuki.rationale.preview.PreviewRationaleStateProvider
import me.samuki.rationale.state.permission.RationalePermissionContent
import me.samuki.rationale.state.success.RationaleSuccessContent

@Composable
public fun RationaleScreen(
    playable: Playable,
    setType: SetType,
    rationaleNavigation: RationaleNavigation
) {
    val viewModel: RationaleViewModel = hiltViewModel()

    LaunchedEffect(Unit) {
        viewModel.onEvent(RationaleContract.Event.SetupScreen(playable, setType))

        viewModel.eventsFlow.collect { effect ->
            when (effect) {
                RationaleContract.Effect.GoBackToList -> rationaleNavigation.backToList()
                RationaleContract.Effect.NavigateToSettings -> rationaleNavigation.navigateToSettings()
            }
        }
    }

    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_START) {
                viewModel.onEvent(RationaleContract.Event.ReturnedFromSettings)
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    val state by viewModel.state.collectAsStateWithLifecycle()
    val onEvent by remember { mutableStateOf(viewModel::onEvent) }

    RationaleContent(
        state = state,
        onEvent = onEvent
    )
}

@Composable
private fun RationaleContent(
    state: RationaleContract.State,
    onEvent: (RationaleContract.Event) -> Unit
) {
    when (state.screenState) {
        RationaleContract.ScreenState.PERMISSION_REQUEST -> RationalePermissionContent(
            onEvent = onEvent
        )
        RationaleContract.ScreenState.SUCCESS -> RationaleSuccessContent(
            onEvent = onEvent
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RationaleContentPreview(
    @PreviewParameter(PreviewRationaleStateProvider::class) state: RationaleContract.State
) {
    Surface {
        RationaleContent(
            state = state,
            onEvent = {}
        )
    }
}
