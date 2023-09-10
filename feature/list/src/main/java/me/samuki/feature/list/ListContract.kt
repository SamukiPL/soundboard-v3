package me.samuki.feature.list

import me.samuki.domain.params.ListType
import me.samuki.model.Playable

internal interface ListContract {

    data class State(
        val itemsType: ListType = ListType.All,
        val items: List<Playable> = emptyList()
    )

    sealed interface Event {
        data object Init : Event
        data class Play(
            val playable: Playable
        ) : Event
    }

    sealed interface Effect {

    }
}
