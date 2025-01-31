package me.samuki.feature.compilation.presentation.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import me.samuki.core.ui.previews.PreviewCombinableProvider
import me.samuki.feature.compilation.domain.model.CombinedCombinable
import me.samuki.feature.compilation.presentation.items.creator.CompilationCreatorItem
import me.samuki.model.values.Id

internal class PreviewCompilationCreatorItemProvider(
    private val combinableProvider: PreviewCombinableProvider = PreviewCombinableProvider()
) : PreviewParameterProvider<CompilationCreatorItem> {

    override val values: Sequence<CompilationCreatorItem>
        get() = combinableProvider.values.mapIndexed { index, combinable ->
            CompilationCreatorItem(
                id = Id(index),
                combinedCombinable = CombinedCombinable(
                    id = Id(index),
                    combinable = combinable
                )
            )
        }
}
