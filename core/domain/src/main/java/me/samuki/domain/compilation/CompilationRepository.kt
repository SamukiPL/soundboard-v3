package me.samuki.domain.compilation

import kotlinx.coroutines.flow.Flow
import me.samuki.model.Combinable
import me.samuki.model.Compilation
import me.samuki.model.NoAnswer
import me.samuki.model.values.Id
import me.samuki.model.values.Name

public interface CompilationRepository {

    public suspend fun observeCompilations(): Flow<List<Compilation>>

    public suspend fun createCompilation(name: Name, list: List<Combinable>): Result<NoAnswer>

    public suspend fun deleteCompilation(id: Id): Result<NoAnswer>
}
