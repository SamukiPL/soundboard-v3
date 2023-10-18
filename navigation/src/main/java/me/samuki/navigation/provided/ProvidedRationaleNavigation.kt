package me.samuki.navigation.provided

import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import me.samuki.rationale.RationaleNavigation

internal class ProvidedRationaleNavigation(
    navigator: DestinationsNavigator
) : RationaleNavigation, DestinationsNavigator by navigator {
    override fun backToList() {
        TODO("Not yet implemented")
    }
}
