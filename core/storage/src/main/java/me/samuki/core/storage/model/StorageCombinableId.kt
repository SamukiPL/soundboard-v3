package me.samuki.core.storage.model

import java.util.UUID

/**
 * Tech-Debt
 * Sqldelight doesn't support internal classes
 * https://github.com/cashapp/sqldelight/issues/1188
 */
@JvmInline
public value class StorageCombinableId(
    public val value: String = UUID.randomUUID().toString()
)
