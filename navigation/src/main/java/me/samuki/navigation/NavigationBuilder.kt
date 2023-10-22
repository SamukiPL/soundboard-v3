package me.samuki.navigation

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.DestinationsNavHost
import me.samuki.navigation.destinations.NavGraphs

@Composable
public fun NavigationBuilder() {
    DestinationsNavHost(
        navGraph = NavGraphs.root,
    )
}
