package me.samuki.feature.compilation.presentation

import me.samuki.model.util.emptyName
import me.samuki.feature.compilation.domain.model.CombinedCombinable
import me.samuki.feature.compilation.presentation.items.creator.CompilationCreatorItem
import me.samuki.feature.compilation.presentation.items.sounds.CompilationCreatorSoundItem
import me.samuki.model.Pause
import me.samuki.model.Sound
import me.samuki.model.values.Name
import me.samuki.model.values.Query

internal typealias CreateButtonVisible = Boolean
internal typealias SetNameDialogVisible = Boolean

internal interface CreatorContract {

    data class State(
        val sounds: List<CompilationCreatorSoundItem> = emptyList(),
        val list: List<CompilationCreatorItem> = emptyList(),
        val showCreateButton: CreateButtonVisible = false,
        val query: Query = Query.Empty,
        val showSetNameDialog: SetNameDialogVisible = false, //TODO change to enum
        val name: Name = emptyName(),
    )

    sealed interface Event {

        data object Init : Event

        data object GoBack : Event

        data class AddSound(
            val sound: Sound,
        ) : Event

        data class AddPause(
            val pause: Pause,
        ) : Event

        data class RemoveCombinable(
            val combinedCombinable: CombinedCombinable,
        ) : Event

        data class SetQuery(
            val query: Query.Text
        ) : Event

        data object RemoveQuery : Event

        data object StartSettingName : Event

        data class SetName(
            val name: Name
        ) : Event

        data object EndCreation : Event
    }

    sealed interface Effect {

        data object GoBackToList : Effect
    }
}
