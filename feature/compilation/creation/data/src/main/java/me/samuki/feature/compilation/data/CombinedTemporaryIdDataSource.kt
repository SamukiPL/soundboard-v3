package me.samuki.feature.compilation.data

import me.samuki.model.values.Id
import javax.inject.Inject

internal class CombinedTemporaryIdDataSource @Inject constructor() {

    private var idCounter: Int = 0

    fun generateId(): Id = Id(++idCounter)
}
