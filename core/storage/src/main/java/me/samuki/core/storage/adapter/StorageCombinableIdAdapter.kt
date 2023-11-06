package me.samuki.core.storage.adapter

import app.cash.sqldelight.ColumnAdapter
import me.samuki.core.storage.model.StorageCombinableId

internal class StorageCombinableIdAdapter : ColumnAdapter<StorageCombinableId, String> {
    override fun decode(databaseValue: String): StorageCombinableId =
        StorageCombinableId(databaseValue)

    override fun encode(value: StorageCombinableId): String = value.value
}
