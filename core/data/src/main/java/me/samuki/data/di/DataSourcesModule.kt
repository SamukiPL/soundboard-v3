package me.samuki.data.di

import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import me.samuki.common.di.DispatcherIO
import me.samuki.model.filters.ListType
import kotlin.enums.EnumEntries

@Module
@InstallIn(SingletonComponent::class)
internal object DataSourcesModule {

    @Provides
    fun listTypes(): EnumEntries<ListType> = ListType.entries

    @Provides
    @DataCoroutinesScope
    fun coroutinesScope(
        @DispatcherIO dispatcher: CoroutineDispatcher
    ): CoroutineScope =
        CoroutineScope(SupervisorJob() + dispatcher + CoroutineExceptionHandler { coroutineContext, throwable ->
            Log.e(
                "TODO DATA MODULE",
                "TODO message for DATA MODULE $throwable $coroutineContext",
                throwable,
            )
        })
}
