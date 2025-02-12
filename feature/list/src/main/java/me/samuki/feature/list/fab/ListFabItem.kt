package me.samuki.feature.list.fab

import me.samuki.feature.list.ListContract


internal enum class ListFabItem(
    val event: ListContract.Event,
) {
    AddCompilation(event = ListContract.Event.AddCompilation),
    GetMoreSoundboards(event = ListContract.Event.GetMoreSoundboards),
}
