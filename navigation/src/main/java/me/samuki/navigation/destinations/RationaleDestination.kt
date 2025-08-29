package me.samuki.navigation.destinations

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import me.samuki.domain.params.SetType
import me.samuki.model.Playable
import me.samuki.navigation.graph.SoundboardGraph
import me.samuki.navigation.provided.ProvidedRationaleNavigation
import me.samuki.rationale.RationaleScreen

@Composable
@Destination<SoundboardGraph>
internal fun Rationale(
    playable: Playable,
    setType: SetType,
    navigator: DestinationsNavigator
) {
    RationaleScreen(
        playable = playable,
        setType = setType,
        rationaleNavigation = ProvidedRationaleNavigation(
            context = LocalContext.current,
            navigator = navigator
        )
    )
}
