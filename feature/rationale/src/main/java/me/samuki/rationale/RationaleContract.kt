package me.samuki.rationale

import me.samuki.model.util.EMPTY_STRING
import me.samuki.domain.params.SetType
import me.samuki.model.Playable
import me.samuki.model.values.Name

internal interface RationaleContract {

    enum class ScreenState {
        PERMISSION_REQUEST,
        SUCCESS
    }

    data class State(
        val playable: Playable? = null,
        val setType: SetType? = null,
        val playableName: Name = Name(EMPTY_STRING),
        val screenState: ScreenState = ScreenState.PERMISSION_REQUEST
    )

    sealed interface Event {

        data class SetupScreen(
            val playable: Playable,
            val setType: SetType
        ) : Event

        data object OpenSettings : Event

        data object GoBack : Event

        data object ReturnedFromSettings : Event
    }

    sealed interface Effect {

        data object GoBackToList : Effect
        data object NavigateToSettings : Effect
    }

}
