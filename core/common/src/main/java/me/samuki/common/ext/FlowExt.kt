package me.samuki.common.ext

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

public inline fun <T, R> Flow<List<T>>.mapList(crossinline transform: (T) -> R): Flow<List<R>> = map { list ->
    list.map(transform)
}

public inline fun <T> Flow<List<T>>.filterList(crossinline predicate: (T) -> Boolean): Flow<List<T>> = map { list ->
    list.filter(predicate)
}
