package me.samuki.domain.sound

import kotlinx.coroutines.flow.Flow
import me.samuki.model.Likeable
import me.samuki.model.NoAnswer
import me.samuki.model.Sound

public interface SoundRepository {

    public suspend fun observeSounds(): Flow<List<Sound>>

    public suspend fun makeFavourite(likeable: Likeable): Result<NoAnswer>

    public suspend fun makeNormal(likeable: Likeable): Result<NoAnswer>
}
