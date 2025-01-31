package me.samuki.feature.list

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import me.samuki.domain.params.SetType
import me.samuki.feature.list.items.ListItem
import me.samuki.feature.list.toolbar.ToolbarState
import me.samuki.model.Likeable
import me.samuki.model.Playable
import me.samuki.model.filters.Filter
import me.samuki.model.values.Query

internal interface ListContract {

    @Stable
    class State(
        items: List<ListItem> = emptyList(),
        toolbarState: ToolbarState = ToolbarState(),
    ) {
        var items: List<ListItem> by mutableStateOf(items)
        val toolbarState: ToolbarState by mutableStateOf(toolbarState)
    }

    sealed interface Event {

        data object Init : Event

        data class Play(
            val playable: Playable
        ) : Event

        data object StopPlaying : Event

        data class ChangeFavouriteState(
            val likeable: Likeable
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

        object GoToCompilationCreation : Effect
    }
}
