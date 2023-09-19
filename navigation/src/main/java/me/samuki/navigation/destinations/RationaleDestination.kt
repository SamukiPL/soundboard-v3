package me.samuki.navigation.destinations

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import me.samuki.model.Playable
import me.samuki.navigation.provided.ProvidedRationaleNavigation
import me.samuki.rationale.RationaleScreen

@Composable
@Destination
@RootNavGraph
internal fun Rationale(
    playable: Playable,
    navigator: DestinationsNavigator
) {
    RationaleScreen(
        playable = playable,
        rationaleNavigation = ProvidedRationaleNavigation(navigator)
    )
}
