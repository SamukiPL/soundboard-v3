package me.samuki.feature.compilation.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.samuki.domain.playable.ObservePlayables
import me.samuki.domain.search.SearchByQuery
import me.samuki.feature.compilation.domain.logic.AddPause
import me.samuki.feature.compilation.domain.logic.AddSound
import me.samuki.feature.compilation.domain.logic.EndCreation
import me.samuki.feature.compilation.domain.logic.ObserveCompilationCreation
import me.samuki.feature.compilation.domain.logic.PlayTemporaryCompilation
import me.samuki.feature.compilation.domain.logic.RemoveCombinable
import me.samuki.feature.compilation.domain.logic.ShareTemporaryCompilation
import me.samuki.feature.compilation.presentation.items.creator.toItem
import me.samuki.feature.compilation.presentation.items.sounds.toItem
import me.samuki.model.Sound
import me.samuki.model.values.Query
import javax.inject.Inject

@HiltViewModel
internal class CompilationCreatorViewModel @Inject constructor(
    private val observePlayables: ObservePlayables,
    private val observeCompilationCreation: ObserveCompilationCreation,
    private val playTemporaryCompilation: PlayTemporaryCompilation,
    private val shareTemporaryCompilation: ShareTemporaryCompilation,
    private val addPause: AddPause,
    private val addSound: AddSound,
    private val removeCombinable: RemoveCombinable,
    private val searchByQuery: SearchByQuery,
    private val endCreation: EndCreation,
) : ViewModel() {

    private val _state = MutableStateFlow(CreatorContract.State())
    val state: StateFlow<CreatorContract.State> = _state

    private val eventChannel = Channel<CreatorContract.Effect>(Channel.BUFFERED)
    val eventsFlow: Flow<CreatorContract.Effect> = eventChannel.receiveAsFlow()

    fun onEvent(event: CreatorContract.Event) {
        viewModelScope.launch {
            eventDispatcher(event)
        }
    }

    private suspend fun eventDispatcher(event: CreatorContract.Event): Any = when (event) {
        CreatorContract.Event.Init -> init()
        CreatorContract.Event.GoBack -> handleBack()
        is CreatorContract.Event.AddSound -> addSound(event.sound)
        is CreatorContract.Event.AddPause -> addPause(event.pause)
        is CreatorContract.Event.RemoveCombinable -> removeCombinable(event.combinedCombinable)
        is CreatorContract.Event.SetQuery -> searchByQuery(event.query)
        CreatorContract.Event.RemoveQuery -> searchByQuery(Query.Empty)
        CreatorContract.Event.StartSettingName -> startSettingName()
        is CreatorContract.Event.SetName -> _state.update { state -> state.copy(name = event.name) }
        CreatorContract.Event.EndCreation -> endCompilationCreation()
        CreatorContract.Event.PlayCompilation -> playTemporaryCompilation
        CreatorContract.Event.ShareCompilation -> shareTemporaryCompilation()
    }

    private suspend fun init() {
        observePlayables().collect { playables ->
            _state.update { state ->
                state.copy(
                    sounds = playables.mapNotNull { it.toItem() }
                )
            }
        }
        observeCompilationCreation().collect { newList ->
            _state.update { state ->
                state.copy(
                    list = newList.map { it.toItem() },
                    showCreateButton = newList.any { it.combinable is Sound },
                )
            }
        }
    }

    private suspend fun handleBack() = when (state.value.showSetNameDialog) {
        false -> eventChannel.send(CreatorContract.Effect.GoBackToList)
        true -> _state.update { state ->
            state.copy(
                showSetNameDialog = false
            )
        }
    }

    private fun startSettingName() = _state.update { state ->
        state.copy(
            showSetNameDialog = true
        )
    }

    private suspend fun endCompilationCreation() = with(state.value) {
        if (list.isEmpty() || name.isEmpty()) return@with

        endCreation(name)
    }
}
