package me.samuki.feature.compilation.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.receiveAsFlow
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
import me.samuki.feature.compilation.presentation.bottom.pause.pause
import me.samuki.feature.compilation.presentation.items.creator.toItem
import me.samuki.feature.compilation.presentation.items.sounds.toItem
import me.samuki.model.Sound
import me.samuki.model.values.Name
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

    internal var state: CreatorContract.State by mutableStateOf(CreatorContract.State())
        private set

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
        CreatorContract.Event.ChangeVolume -> state.volumeEnabled = state.volumeEnabled.not()
        is CreatorContract.Event.AddSound -> addSound(event.sound)
        is CreatorContract.Event.AddPause -> addPause(event.item.pause)
        is CreatorContract.Event.RemoveCombinable -> removeCombinable(event.combinedCombinable)
        is CreatorContract.Event.SetQuery -> updateQuery(event.query)
        CreatorContract.Event.RemoveQuery -> updateQuery(Query.Empty)
        CreatorContract.Event.StartSettingName -> startSettingName()
        is CreatorContract.Event.SetName -> updateName(event.name)
        CreatorContract.Event.EndCreation -> endCompilationCreation()
        CreatorContract.Event.PlayCompilation -> playTemporaryCompilation()
        CreatorContract.Event.ShareCompilation -> shareTemporaryCompilation()
    }

    private suspend fun init() {
        observePlayables().combine(observeCompilationCreation()) { playables, newList ->
            state.sounds = playables.mapNotNull { it.toItem() }
            state.list = newList.map { it.toItem() }
            state.showCreateButton = newList.any { it.combinable is Sound }
        }.collect()
    }

    private suspend fun handleBack() = when (state.showSetNameDialog) {
        false -> eventChannel.send(CreatorContract.Effect.GoBackToList)
        true -> state.showSetNameDialog = false
    }

    private suspend fun updateQuery(newQuery: Query) {
        searchByQuery(newQuery)
        state.bottomBarState.queryViewState.apply {
            this.query = newQuery
            showQueryPlaceholder = newQuery == Query.Empty
        }
    }

    private fun startSettingName() {
        state.showSetNameDialog = true
    }

    private fun updateName(name: Name) {
        state.finishDialogState.apply {
            this.name = name
            showNamePlaceholder = name.isEmpty()
            finishDialogState = name.isNotEmpty()
        }
    }

    private suspend fun endCompilationCreation() = with(state) {
        if (list.isEmpty() || finishDialogState.name.isEmpty()) return@with

        endCreation(finishDialogState.name)
    }
}
