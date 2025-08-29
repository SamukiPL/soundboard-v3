package me.samuki.navigation.provided

import com.ramcosta.composedestinations.generated.destinations.CompilationCreationSuccessDestination
import com.ramcosta.composedestinations.generated.destinations.ListDestination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import me.samuki.feature.compilation.presentation.CompilationCreatorNavigation

internal class ProvidedCompilationCreatorNavigation(
    navigator: DestinationsNavigator
) : CompilationCreatorNavigation, DestinationsNavigator by navigator {

    override fun goBack() {
        navigateUp()
    }

    override fun goToCreationSuccessScreen() {
        navigate(
            direction = CompilationCreationSuccessDestination(),
        ) {
            popBackStack(
                route = ListDestination(),
                inclusive = false
            )
        }
    }
}
