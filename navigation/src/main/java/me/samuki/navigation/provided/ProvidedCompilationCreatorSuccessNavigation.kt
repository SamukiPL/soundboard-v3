package me.samuki.navigation.provided

import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import me.samuki.compilation.success.CompilationCreationSuccessNavigation

internal class ProvidedCompilationCreatorSuccessNavigation(
    private val navigator: DestinationsNavigator
) : CompilationCreationSuccessNavigation, DestinationsNavigator by navigator {

    override fun goToList() {
        navigateUp()
    }
}
