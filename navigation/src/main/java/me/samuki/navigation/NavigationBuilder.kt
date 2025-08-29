package me.samuki.navigation

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.generated.NavGraphs

@Composable
public fun NavigationBuilder() {
    DestinationsNavHost(navGraph = NavGraphs.soundboard)
}
