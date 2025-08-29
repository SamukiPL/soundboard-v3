package me.samuki.navigation.destinations

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import me.samuki.compilation.success.CompilationCreationSuccessScreen
import me.samuki.navigation.graph.SoundboardGraph
import me.samuki.navigation.provided.ProvidedCompilationCreatorSuccessNavigation

@Composable
@Destination<SoundboardGraph>
internal fun CompilationCreationSuccess(
    navigator: DestinationsNavigator
) {

    CompilationCreationSuccessScreen(
        navigation = ProvidedCompilationCreatorSuccessNavigation(navigator)
    )
}
