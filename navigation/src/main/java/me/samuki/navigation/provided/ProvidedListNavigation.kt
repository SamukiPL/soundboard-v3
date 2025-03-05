package me.samuki.navigation.provided

import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import me.samuki.domain.params.SetType
import me.samuki.feature.list.ListNavigation
import me.samuki.model.Playable
import me.samuki.navigation.destinations.destinations.CompilationCreationDestination
import me.samuki.navigation.destinations.destinations.RationaleDestination
import me.samuki.navigation.destinations.destinations.PromotionDestination

internal class ProvidedListNavigation(
    navigator: DestinationsNavigator
) : ListNavigation, DestinationsNavigator by navigator {

    override fun goToSettingsRationale(playable: Playable, setType: SetType) {
        navigate(
            RationaleDestination(
                playable,
                setType
            )
        )
    }

    override fun goToCompilationCreation() {
        navigate(
            CompilationCreationDestination()
        )
    }

    override fun goToMoreSoundboards() {
        navigate(
            PromotionDestination()
        )
    }
}
