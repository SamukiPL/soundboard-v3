package me.samuki.domain.sound

import kotlinx.coroutines.flow.Flow
import me.samuki.model.NoAnswer
import me.samuki.model.Sound

public interface SoundRepository {

    public suspend fun observeSounds(): Flow<List<Sound>>

    public suspend fun makeFavourite(sound: Sound): Result<NoAnswer>

    public suspend fun makeNormal(sound: Sound): Result<NoAnswer>
}
