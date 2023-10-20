package me.samuki.feature.list

import me.samuki.domain.params.SetType
import me.samuki.feature.list.items.ListItem
import me.samuki.feature.list.toolbar.ToolbarState
import me.samuki.feature.list.toolbar.filters.FilterItem
import me.samuki.model.Playable
import me.samuki.model.Sound
import me.samuki.model.filters.Filter
import me.samuki.model.values.Query

internal interface ListContract {

    data class State(
        val items: List<ListItem> = emptyList(),
        val filters: List<FilterItem> = emptyList(),
        val toolbarState: ToolbarState = ToolbarState(),
    )

    sealed interface Event {

        data object Init : Event

        data class Play(
            val playable: Playable
        ) : Event

        data object StopPlaying : Event

        data class ChangeFavouriteState(
            val sound: Sound
        ) : Event

        data class Share(
            val playable: Playable
        ) : Event

        data class SetAsNotification(
            val playable: Playable
        ) : Event

        data class SetAsRingtone(
            val playable: Playable
        ) : Event

        data class OpenQueryToolbar(
            val query: Query
        ) : Event

        data object AcceptQuery : Event

        data class SetQuery(
            val query: Query.Text
        ) : Event

        data object RemoveQuery : Event

        data class ChangeFilter(
            val filter: Filter
        ) : Event
    }

    sealed interface Effect {

        data class GoToSettingsRationale(
            val playable: Playable,
            val setType: SetType
        ) : Effect
    }
}
