package me.samuki.navigation.provided

import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import me.samuki.feature.list.ListNavigation

internal class ProvidedListNavigation(
    navigator: DestinationsNavigator
) : ListNavigation, DestinationsNavigator by navigator
