package me.samuki.feature.compilation.domain

import kotlinx.coroutines.flow.StateFlow
import me.samuki.feature.compilation.domain.model.CombinedCombinable
import me.samuki.model.NoAnswer
import me.samuki.model.Pause
import me.samuki.model.Sound
import me.samuki.model.values.Name

public interface CompilationCreationFeatureRepository {

    public val items: StateFlow<List<CombinedCombinable>>

    public suspend fun addSound(sound: Sound): Result<NoAnswer>

    public suspend fun addPause(pause: Pause): Result<NoAnswer>

    public suspend fun removeCombinable(item: CombinedCombinable): Result<NoAnswer>

    public suspend fun endCreation(name: Name): Result<NoAnswer>
}
