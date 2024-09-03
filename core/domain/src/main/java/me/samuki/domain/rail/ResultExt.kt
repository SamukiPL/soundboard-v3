package me.samuki.domain.rail

import kotlin.coroutines.CoroutineContext
import kotlinx.coroutines.withContext
import me.samuki.model.NoAnswer

public typealias ListResult<T> = Result<List<T>>

public inline fun runNoAnswer(block: () -> Unit): Result<NoAnswer> {
    return try {
        block()
        Result.success(NoAnswer)
    } catch (e: Throwable) {
        Result.failure(e)
    }
}

public suspend inline fun <T> runCatchingWithContext(
    coroutineContext: CoroutineContext,
    crossinline block: suspend () -> T,
): Result<T> = withContext(coroutineContext) { runCatching { block() } }

public inline infix fun <T, R> Result<T>.andThen(transform: (T) -> Result<R>): Result<R> =
    runCatching {
        return transform(getOrThrow())
    }
