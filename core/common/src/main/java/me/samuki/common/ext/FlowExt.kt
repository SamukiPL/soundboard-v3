package me.samuki.common.ext

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

inline fun <T, R> Flow<List<T>>.mapList(crossinline transform: (T) -> R) = map { list ->
    list.map(transform)
}
