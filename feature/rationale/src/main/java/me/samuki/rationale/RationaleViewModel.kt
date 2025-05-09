package me.samuki.rationale

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.samuki.domain.playable.SetPlayable
import javax.inject.Inject

@HiltViewModel
internal class RationaleViewModel @Inject constructor(
    private val setPlayable: SetPlayable
) : ViewModel() {

    private val _state = MutableStateFlow(RationaleContract.State())
    val state: StateFlow<RationaleContract.State> = _state

    private val eventChannel = Channel<RationaleContract.Effect>(Channel.BUFFERED)
    val eventsFlow = eventChannel.receiveAsFlow()

    fun onEvent(event: RationaleContract.Event) {
        viewModelScope.launch {
            eventDispatcher(event)
        }
    }

    private suspend fun eventDispatcher(event: RationaleContract.Event): Any = when (event) {
        is RationaleContract.Event.SetupScreen -> _state.update {
            it.copy(
                playable = event.playable,
                setType = event.setType,
                playableName = event.playable.name
            )
        }
        RationaleContract.Event.GoBack -> eventChannel.send(RationaleContract.Effect.GoBackToList)
        RationaleContract.Event.OpenSettings -> eventChannel.send(RationaleContract.Effect.NavigateToSettings)
        RationaleContract.Event.ReturnedFromSettings -> state.value.let { currentState ->
            if (currentState.playable == null || currentState.setType == null) return@let
            
            setPlayable(currentState.playable, currentState.setType)
                .onSuccess {
                    _state.update { it.copy(screenState = RationaleContract.ScreenState.SUCCESS) }
                }
        }
    }
}
