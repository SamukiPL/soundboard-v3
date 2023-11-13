package me.samuki.core.storage

import kotlinx.coroutines.flow.Flow
import me.samuki.core.storage.model.StorageCombinable
import me.samuki.core.storage.model.StorageCompilation
import me.samuki.model.values.Id
import me.samuki.model.values.Name

public interface CompilationsDataSource {

    public val compilationsFlow: Flow<List<StorageCompilation>>

    public fun addCompilation(name: Name, combinables: List<StorageCombinable>)

    public fun deleteCompilation(id: Id)
}
