package me.samuki.core.storage.dao

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import me.samuki.common.di.DispatcherIO
import me.samuki.core.storage.di.CompilationQueries
import me.samuki.core.storage.entity.CompilationEntity
import me.samuki.model.values.Id
import me.samuki.model.values.Name
import javax.inject.Inject

internal class CompilationsDao @Inject constructor(
    private val compilationQueries: CompilationQueries,
    @DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {

    fun getCompilations(): Flow<List<CompilationEntity>> =
        compilationQueries.selectAll()
            .asFlow()
            .mapToList(coroutineDispatcher)

    fun insertCompilation(name: Name): Long = compilationQueries.transactionWithResult {
        compilationQueries.insertCompilation(
            id = null,
            name = name
        )
        compilationQueries.lastInsertRowId().executeAsOne()
    }

    fun deleteCompilation(id: Id) = compilationQueries.deleteCompilation(id)
}
