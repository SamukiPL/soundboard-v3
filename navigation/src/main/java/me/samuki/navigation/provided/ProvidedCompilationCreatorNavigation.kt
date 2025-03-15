package me.samuki.navigation.provided

import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import me.samuki.feature.compilation.presentation.CompilationCreatorNavigation
import me.samuki.navigation.destinations.destinations.CompilationCreationSuccessDestination
import me.samuki.navigation.destinations.destinations.ListDestination

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
                route = ListDestination().route,
                inclusive = false
            )
        }
    }
}
