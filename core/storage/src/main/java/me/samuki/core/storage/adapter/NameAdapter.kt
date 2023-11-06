package me.samuki.core.storage.adapter

import app.cash.sqldelight.ColumnAdapter
import me.samuki.model.values.Name

internal class NameAdapter : ColumnAdapter<Name, String> {
    override fun decode(databaseValue: String): Name = Name(databaseValue)

    override fun encode(value: Name): String = value.value
}
