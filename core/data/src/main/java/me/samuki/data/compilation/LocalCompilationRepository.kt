package me.samuki.data.compilation

import kotlinx.coroutines.flow.Flow
import me.samuki.domain.compilation.CompilationRepository
import me.samuki.model.Compilation
import me.samuki.model.NoAnswer
import me.samuki.model.Sound
import me.samuki.model.values.Id
import me.samuki.model.values.Name
import me.samuki.model.values.Query
import javax.inject.Inject

internal class LocalCompilationRepository @Inject constructor() : CompilationRepository {
    override suspend fun observeCompilations(): Flow<List<Compilation>> {
        TODO("Not yet implemented")
    }

    override suspend fun searchByQuery(query: Query): Result<NoAnswer> {
        TODO("Not yet implemented")
    }

    override suspend fun clearSearch(): Result<NoAnswer> {
        TODO("Not yet implemented")
    }

    override suspend fun createCompilation(name: Name, list: List<Sound>): Result<NoAnswer> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteCompilation(id: Id): Result<NoAnswer> {
        TODO("Not yet implemented")
    }

    override suspend fun editCompilation(id: Id, name: Name, list: List<Sound>): Result<NoAnswer> {
        TODO("Not yet implemented")
    }
}
