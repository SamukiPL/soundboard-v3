package me.samuki.navigation.provided

import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import me.samuki.feature.list.ListNavigation
import me.samuki.model.Playable
import me.samuki.navigation.destinations.destinations.RationaleDestination

internal class ProvidedListNavigation(
    navigator: DestinationsNavigator
) : ListNavigation, DestinationsNavigator by navigator {

    override fun goToSettingsRationale(playable: Playable) {
        navigate(
            RationaleDestination(
                playable
            )
        )
    }
}
