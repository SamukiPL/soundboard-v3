package me.samuki.feature.compilation.presentation

import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import me.samuki.feature.compilation.domain.model.CombinedCombinable
import me.samuki.feature.compilation.presentation.bottom.BottomBarState
import me.samuki.feature.compilation.presentation.items.creator.CompilationCreatorItem
import me.samuki.feature.compilation.presentation.items.sounds.CompilationCreatorSound
import me.samuki.model.Pause
import me.samuki.model.Sound
import me.samuki.model.util.emptyName
import me.samuki.model.values.Name
import me.samuki.model.values.Query

internal typealias CreateButtonVisible = Boolean
internal typealias SetNameDialogVisible = Boolean

internal interface CreatorContract {

    @Stable
    class State(
        sounds: List<CompilationCreatorSound> = mutableStateListOf(),
        list: List<CompilationCreatorItem> = mutableStateListOf(),
        showCreateButton: CreateButtonVisible = false,
        bottomBarState: BottomBarState = BottomBarState(),
        showSetNameDialog: SetNameDialogVisible = false, //TODO change to enum
        name: Name = emptyName(),
    ) {
        var sounds: List<CompilationCreatorSound> by mutableStateOf(sounds)
        var list: List<CompilationCreatorItem> by mutableStateOf(list)
        var showCreateButton: CreateButtonVisible by mutableStateOf(showCreateButton)
        var bottomBarState: BottomBarState by mutableStateOf(bottomBarState)
        var showSetNameDialog: SetNameDialogVisible by mutableStateOf(showSetNameDialog) //TODO change to enum
        var name: Name by mutableStateOf(name)
    }

    sealed interface Event {

        data object Init : Event

        data object GoBack : Event

        data object PlayCompilation : Event

        data object ShareCompilation : Event

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
