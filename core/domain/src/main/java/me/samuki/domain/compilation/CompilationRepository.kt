package me.samuki.domain.compilation

import kotlinx.coroutines.flow.Flow
import me.samuki.model.Compilation
import me.samuki.model.values.Query
import me.samuki.model.values.Id
import me.samuki.model.values.Name
import me.samuki.model.NoAnswer
import me.samuki.model.Sound

public interface CompilationRepository {

    public suspend fun observeCompilations(): Flow<List<Compilation>>

    public suspend fun searchByQuery(query: Query): Result<NoAnswer>

    public suspend fun clearSearch(): Result<NoAnswer>

    public suspend fun createCompilation(name: Name, list: List<Sound>): Result<NoAnswer>

    public suspend fun deleteCompilation(id: Id): Result<NoAnswer>

    public suspend fun editCompilation(id: Id, name: Name, list: List<Sound>): Result<NoAnswer>
}
