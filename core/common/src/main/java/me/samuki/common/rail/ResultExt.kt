package me.samuki.common.rail

import me.samuki.model.NoAnswer

typealias ListResult<T> = Result<List<T>>

inline fun runNoAnswer(block: () -> Unit): Result<NoAnswer> {
    return try {
        block()
        Result.success(NoAnswer)
    } catch (e: Throwable) {
        Result.failure(e)
    }
}
