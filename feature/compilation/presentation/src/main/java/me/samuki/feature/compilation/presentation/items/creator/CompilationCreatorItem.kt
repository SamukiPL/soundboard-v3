package me.samuki.feature.compilation.presentation.items.creator

import androidx.compose.runtime.Stable
import me.samuki.core.ui.value.Label
import me.samuki.feature.compilation.domain.model.CombinedCombinable
import me.samuki.model.values.Id

@Stable
internal data class CompilationCreatorItem(
    val id: Id,
    val combinedCombinable: CombinedCombinable,
)
