package me.samuki.core.storage.model

import me.samuki.model.values.Id
import me.samuki.model.values.Supplement

public sealed interface StorageCombinable

public data class StoragePause(
    public val repeats: Long
) : StorageCombinable

public data class StorageSound(
    public val resourceId: Id,
    public val supplement: Supplement
) : StorageCombinable
