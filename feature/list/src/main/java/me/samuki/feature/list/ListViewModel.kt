package me.samuki.feature.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import me.samuki.domain.playable.ObservePlayables
import me.samuki.domain.playable.PlayPlayable
import javax.inject.Inject

@HiltViewModel
internal class ListViewModel @Inject constructor(
    private val observePlayables: ObservePlayables,
    private val playPlayable: PlayPlayable
) : ViewModel() {

    private val _state = MutableStateFlow(ListContract.State())
    val state: StateFlow<ListContract.State> = _state

    private val eventChannel = Channel<ListContract.Effect>(Channel.BUFFERED)
    val eventsFlow = eventChannel.receiveAsFlow()

    fun onEvent(event: ListContract.Event) {
        viewModelScope.launch {
            eventDispatcher(event)
        }
    }

    private suspend fun eventDispatcher(event: ListContract.Event): Any = when (event) {
        ListContract.Event.Init -> initViewModel()
        is ListContract.Event.Play -> playPlayable(event.playable)
    }

    private suspend fun initViewModel() {
        state
            .map { it.itemsType }
            .distinctUntilChanged()
            .flatMapConcat { type ->
                observePlayables(type)
            }
            .collect { playables ->
                _state.getAndUpdate {
                    it.copy(
                        items = playables
                    )
                }
            }
    }
}
