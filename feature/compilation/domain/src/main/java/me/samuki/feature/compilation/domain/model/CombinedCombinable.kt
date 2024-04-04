package me.samuki.feature.compilation.domain.model

import me.samuki.model.Combinable
import me.samuki.model.values.Id

public data class CombinedCombinable(
    val id: Id,
    val combinable: Combinable,
)
