package me.samuki.domain

import me.samuki.model.Compilation
import me.samuki.model.NoAnswer
import me.samuki.model.Sound

public interface ShareRepository {

    public suspend fun shareSound(sound: Sound): Result<NoAnswer>

    public suspend fun shareCompilation(compilation: Compilation): Result<NoAnswer>
}
