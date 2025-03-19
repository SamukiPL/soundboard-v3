package me.samuki.navigation.destinations

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import me.samuki.compilation.success.CompilationCreationSuccessScreen
import me.samuki.navigation.provided.ProvidedCompilationCreatorSuccessNavigation

@Composable
@Destination
@RootNavGraph
internal fun CompilationCreationSuccess(
    navigator: DestinationsNavigator
) {

    CompilationCreationSuccessScreen(
        navigation = ProvidedCompilationCreatorSuccessNavigation(navigator)
    )
}
