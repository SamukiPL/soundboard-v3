package me.samuki.common.rail

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

public inline infix fun <T, R> Result<T>.andThen(transform: (T) -> Result<R>): Result<R> =
    runCatching {
        return transform(getOrThrow())
    }
