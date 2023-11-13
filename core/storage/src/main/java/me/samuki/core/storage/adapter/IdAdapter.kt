package me.samuki.core.storage.adapter

import app.cash.sqldelight.ColumnAdapter
import me.samuki.model.values.Id

internal class IdAdapter : ColumnAdapter<Id, Long> {
    override fun decode(databaseValue: Long): Id = Id(databaseValue.toInt())

    override fun encode(value: Id): Long = value.value.toLong()
}
