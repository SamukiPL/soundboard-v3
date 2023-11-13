package me.samuki.common.algorithm

/**
 * Sorts items LinkedList style. Every item has to have its own unique id
 * and reference to another item in list unless it's last item.
 * Every item can be reference only one time. One-to-one relationship
 *
 * @throws IllegalStateException when relations have not been preserved
 */
public fun <TYPE> List<TYPE>.linkedSort(key: (TYPE) -> String, nextKey: (TYPE) -> String?): List<TYPE> {
    val map = associateBy { nextKey(it) }
    return buildList<TYPE> {
        var item = map[null]
        while (item != null) {
            add(0, item)
            item = map[key(item)]
        }
    }.also {
        if (it.size != this@linkedSort.size) {
            throw IllegalStateException("linkedSort should be used only when data has correct relations.")
        }
    }
}
