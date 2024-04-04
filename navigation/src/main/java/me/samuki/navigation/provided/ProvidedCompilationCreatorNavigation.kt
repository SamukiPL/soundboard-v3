package me.samuki.navigation.provided

import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import me.samuki.feature.compilation.presentation.CompilationCreatorNavigation

internal class ProvidedCompilationCreatorNavigation(
    private val navigator: DestinationsNavigator
) : CompilationCreatorNavigation {

    override fun goBack() {
        navigator.navigateUp()
    }

    override fun goToCreationSuccessScreen() {
        TODO("Not yet implemented")
    }
}
