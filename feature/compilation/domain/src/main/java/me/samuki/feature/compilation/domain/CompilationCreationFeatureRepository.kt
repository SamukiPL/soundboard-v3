package me.samuki.feature.compilation.domain

import kotlinx.coroutines.flow.Flow
import me.samuki.feature.compilation.domain.model.CombinedCombinable
import me.samuki.model.NoAnswer
import me.samuki.model.Pause
import me.samuki.model.Sound
import me.samuki.model.values.Name

public interface CompilationCreationFeatureRepository {

    public val items: Flow<List<CombinedCombinable>>

    public suspend fun addSound(sound: Sound): Result<NoAnswer>

    public suspend fun addPause(pause: Pause): Result<NoAnswer>

    public suspend fun removeCombinable(item: CombinedCombinable): Result<NoAnswer>

    public suspend fun endCreation(name: Name): Result<NoAnswer>
}
