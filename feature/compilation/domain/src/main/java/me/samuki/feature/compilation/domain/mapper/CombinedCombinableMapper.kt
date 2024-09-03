package me.samuki.feature.compilation.domain.mapper

import me.samuki.feature.compilation.domain.model.CombinedCombinable
import me.samuki.model.Combinable

public val List<CombinedCombinable>.combinables: List<Combinable>
    get() = map { it.combinable }
