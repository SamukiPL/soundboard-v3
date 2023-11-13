package me.samuki.core.storage.di

import android.content.Context
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.samuki.core.storage.Database
import me.samuki.core.storage.adapter.IdAdapter
import me.samuki.core.storage.adapter.NameAdapter
import me.samuki.core.storage.adapter.StorageCombinableIdAdapter
import me.samuki.core.storage.adapter.SupplementAdapter
import me.samuki.core.storage.entity.CompilationEntity
import me.samuki.core.storage.entity.PauseEntity
import me.samuki.core.storage.entity.SoundEntity
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Singleton
    @Provides
    fun database(
        @ApplicationContext context: Context,
        soundAdapter: SoundEntity.Adapter,
        pauseAdapter: PauseEntity.Adapter,
        compilationAdapter: CompilationEntity.Adapter
    ): Database =
        Database.invoke(
            AndroidSqliteDriver(Database.Schema, context, "TODO.db"),
            compilationAdapter,
            pauseAdapter,
            soundAdapter,
        )

    @Provides
    fun compilationAdapter(): CompilationEntity.Adapter = CompilationEntity.Adapter(
        idAdapter = IdAdapter(),
        nameAdapter = NameAdapter()
    )

    @Provides
    fun soundAdapter(): SoundEntity.Adapter = SoundEntity.Adapter(
        idAdapter = StorageCombinableIdAdapter(),
        compilation_idAdapter = IdAdapter(),
        next_combinable_idAdapter = StorageCombinableIdAdapter(),
        resource_idAdapter = IdAdapter(),
        supplementAdapter = SupplementAdapter()
    )

    @Provides
    fun pauseAdapter(): PauseEntity.Adapter = PauseEntity.Adapter(
        idAdapter = StorageCombinableIdAdapter(),
        compilation_idAdapter = IdAdapter(),
        next_combinable_idAdapter = StorageCombinableIdAdapter(),
    )
}
