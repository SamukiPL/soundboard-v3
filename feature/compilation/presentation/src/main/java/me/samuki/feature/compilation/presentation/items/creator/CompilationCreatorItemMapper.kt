package me.samuki.feature.compilation.presentation.items.creator

import me.samuki.feature.compilation.domain.model.CombinedCombinable

internal fun CombinedCombinable.toItem(): CompilationCreatorItem = CompilationCreatorItem(
    id = id,
    combinedCombinable = this
)
