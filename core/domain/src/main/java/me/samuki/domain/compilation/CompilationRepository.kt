package me.samuki.domain.compilation

import kotlinx.coroutines.flow.Flow
import me.samuki.model.Compilation
import me.samuki.model.values.Query
import me.samuki.model.values.Id
import me.samuki.model.values.Name
import me.samuki.model.NoAnswer
import me.samuki.model.Sound

interface CompilationRepository {

    suspend fun observeCompilations(): Flow<List<Compilation>>

    suspend fun searchByQuery(query: Query): Result<NoAnswer>

    suspend fun clearSearch(): Result<NoAnswer>

    suspend fun createCompilation(name: Name, list: List<Sound>): Result<NoAnswer>

    suspend fun deleteCompilation(id: Id): Result<NoAnswer>

    suspend fun editCompilation(id: Id, name: Name, list: List<Sound>): Result<NoAnswer>
}
