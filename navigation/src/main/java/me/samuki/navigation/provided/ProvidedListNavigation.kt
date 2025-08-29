package me.samuki.navigation.provided

import com.ramcosta.composedestinations.generated.destinations.CompilationCreationDestination
import com.ramcosta.composedestinations.generated.destinations.PromotionDestination
import com.ramcosta.composedestinations.generated.destinations.RationaleDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import me.samuki.domain.params.SetType
import me.samuki.feature.list.ListNavigation
import me.samuki.model.Playable

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
