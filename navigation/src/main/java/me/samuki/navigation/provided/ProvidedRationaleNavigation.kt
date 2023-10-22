package me.samuki.navigation.provided

import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import me.samuki.navigation.destinations.destinations.ListDestination
import me.samuki.navigation.destinations.destinations.RationaleDestination
import me.samuki.rationale.logic.RationaleNavigation

internal class ProvidedRationaleNavigation(
    navigator: DestinationsNavigator
) : RationaleNavigation, DestinationsNavigator by navigator {
    override fun backToList() {
        navigate(ListDestination())
    }
}
