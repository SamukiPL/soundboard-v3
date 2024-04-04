package me.samuki.navigation.destinations

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import me.samuki.feature.compilation.presentation.CompilationCreatorScreen
import me.samuki.navigation.provided.ProvidedCompilationCreatorNavigation

@Composable
@Destination
@RootNavGraph
internal fun CompilationCreation(
    navigator: DestinationsNavigator
) {
    CompilationCreatorScreen(
        navigation = ProvidedCompilationCreatorNavigation(navigator)
    )
}
