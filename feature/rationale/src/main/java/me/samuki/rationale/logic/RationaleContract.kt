package me.samuki.rationale.logic

import me.samuki.domain.params.SetType
import me.samuki.model.Playable
import me.samuki.model.values.Name

internal interface RationaleContract {

    data class State(
        val playable: Playable? = null,
        val setType: SetType? = null,
        val playableName: Name = Name("")
    )

    sealed interface Event {

        data class SetupScreen(
            val playable: Playable,
            val setType: SetType
        ) : Event

        data object OpenSettings : Event

        data object GoBack : Event

        data object SetPlayable : Event
    }

    sealed interface Effect {

        data object GoBackToList : Effect
    }

}
