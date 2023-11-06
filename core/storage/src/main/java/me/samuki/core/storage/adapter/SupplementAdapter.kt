package me.samuki.core.storage.adapter

import app.cash.sqldelight.ColumnAdapter
import me.samuki.model.values.Supplement

internal class SupplementAdapter : ColumnAdapter<Supplement, String> {
    override fun decode(databaseValue: String): Supplement = Supplement(databaseValue)

    override fun encode(value: Supplement): String = value.value
}
