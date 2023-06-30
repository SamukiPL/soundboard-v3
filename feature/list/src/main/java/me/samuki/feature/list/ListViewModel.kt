package me.samuki.feature.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject

@HiltViewModel
internal class ListViewModel @Inject constructor() : ViewModel() {

    var state by mutableStateOf(ListContract.State(emptyList()))
        private set

    private val eventChannel = Channel<ListContract.Effect>(Channel.BUFFERED)
    val eventsFlow = eventChannel.receiveAsFlow()

    fun onEvent(event: ListContract.Event) {

    }
}
