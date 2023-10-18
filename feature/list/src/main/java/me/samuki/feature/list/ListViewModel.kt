package me.samuki.feature.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import me.samuki.domain.filters.ChangeFilterSelectionState
import me.samuki.domain.filters.ObserveAllFilters
import me.samuki.domain.params.SetType
import me.samuki.domain.playable.ObservePlayables
import me.samuki.domain.playable.PlayPlayable
import me.samuki.domain.playable.SetPlayable
import me.samuki.domain.playable.SharePlayable
import me.samuki.domain.playable.StopPlaying
import me.samuki.domain.search.SearchByQuery
import me.samuki.domain.sound.favourite.ChangeFavouriteState
import me.samuki.model.Playable
import me.samuki.model.exceptions.WriteSettingsPermissionNotGrantedException
import me.samuki.model.values.Query
import javax.inject.Inject

@HiltViewModel
internal class ListViewModel @Inject constructor(
    private val observePlayables: ObservePlayables,
    private val playPlayable: PlayPlayable,
    private val stopPlaying: StopPlaying,
    private val sharePlayable: SharePlayable,
    private val favouriteState: ChangeFavouriteState,
    private val setPlayable: SetPlayable,
    private val searchByQuery: SearchByQuery,
    private val observeAllFilters: ObserveAllFilters,
    private val changeFilterSelectionState: ChangeFilterSelectionState
) : ViewModel() {

    private val _state = MutableStateFlow(ListContract.State())
    val state: StateFlow<ListContract.State> = _state

    private val eventChannel = Channel<ListContract.Effect>(Channel.BUFFERED)
    val eventsFlow = eventChannel.receiveAsFlow()

    private val listStateCombiner = ListStateCombiner(_state::update)

    fun onEvent(event: ListContract.Event) {
        viewModelScope.launch {
            eventDispatcher(event)
        }
    }

    private suspend fun eventDispatcher(event: ListContract.Event): Any = when (event) {
        ListContract.Event.Init -> listStateCombiner.manageStateForPlayablesAndFilters(
            playablesFlow = observePlayables(),
            filtersFlow = observeAllFilters(),
        )
        is ListContract.Event.Play -> playPlayable(event.playable)
        ListContract.Event.StopPlaying -> stopPlaying()
        is ListContract.Event.ChangeFavouriteState -> favouriteState(event.sound)
        is ListContract.Event.Share -> sharePlayable(event.playable)
        is ListContract.Event.SetAsNotification -> setPlayableDispatcher(
            playable = event.playable,
            setType = SetType.Notification
        )
        is ListContract.Event.SetAsRingtone -> setPlayableDispatcher(
            playable = event.playable,
            setType = SetType.Ringtone
        )
        is ListContract.Event.ChangeFilter -> changeFilterSelectionState(event.filter)
        is ListContract.Event.OpenQueryToolbar -> listStateCombiner.openQueryToolbar(
            query = event.query,
        )
        is ListContract.Event.AcceptQuery -> listStateCombiner.closeQueryToolbar()
        ListContract.Event.RemoveQuery -> {
            searchByQuery(Query.Empty)
            listStateCombiner.updateQuery(Query.Empty)
            listStateCombiner.closeQueryToolbar(true)
        }
        is ListContract.Event.SetQuery -> {
            listStateCombiner.updateQuery(event.query)
            searchByQuery(event.query)
        }
    }

    private suspend fun setPlayableDispatcher(playable: Playable, setType: SetType) {
        setPlayable(playable, setType).onFailure {
            when (it) {
                is WriteSettingsPermissionNotGrantedException -> eventChannel.send(
                    ListContract.Effect.GoToSettingsRationale(
                        playable
                    )
                )
            }
        }
    }
}
