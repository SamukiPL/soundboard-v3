package me.samuki.feature.compilation.presentation.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import me.samuki.feature.compilation.presentation.CreatorContract
import me.samuki.model.util.emptyName
import me.samuki.model.values.Id
import me.samuki.model.values.Query

internal class PreviewCreatorContractStateProvider(
    private val compilationCreatorItemProvider: PreviewCompilationCreatorItemProvider = PreviewCompilationCreatorItemProvider(),
    private val compilationCreatorSoundProvider: PreviewCompilationCreatorSoundProvider = PreviewCompilationCreatorSoundProvider(),
) : PreviewParameterProvider<CreatorContract.State> {

    override val values: Sequence<CreatorContract.State>
        get() = sequenceOf(
            CreatorContract.State(
                sounds = compilationCreatorSoundProvider.values.toList(),
                list = compilationCreatorItemProvider.values
                    .flatMapIndexed { index, item ->
                        List(3) {
                            item.copy(
                                id = Id(it.plus(1) * index)
                            )
                        }
                    }
                    .shuffled()
                    .toList(),
                showCreateButton = true,
                query = Query.Empty,
                showSetNameDialog = false,
                name = emptyName()
            ),
            CreatorContract.State(
                sounds = listOf(),
                list = listOf(),
                showCreateButton = false,
                query = Query.Empty,
                showSetNameDialog = false,
                name = emptyName()
            )
        )
}
