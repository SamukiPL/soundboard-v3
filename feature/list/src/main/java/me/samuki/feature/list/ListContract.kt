package me.samuki.feature.list

import me.samuki.model.Sound

interface ListContract {
    data class State(
        val items: List<Sound>
    )

    sealed interface Event {

    }

    sealed interface Effect {

    }
}
