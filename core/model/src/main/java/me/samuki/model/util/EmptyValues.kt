package me.samuki.model.util

import me.samuki.model.values.Id
import me.samuki.model.values.Name
import me.samuki.model.values.Supplement

public const val EMPTY_STRING: String = ""
public const val TEMPORARY_ID: Int = 0

public fun emptyName(): Name = Name(EMPTY_STRING)

public fun emptySupplement(): Supplement = Supplement(EMPTY_STRING)

public fun temporaryId(): Id = Id(TEMPORARY_ID)
