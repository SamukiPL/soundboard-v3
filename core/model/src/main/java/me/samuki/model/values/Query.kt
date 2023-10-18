package me.samuki.model.values


public sealed interface Query {

    public data object Empty : Query

    @JvmInline
    public value class Text(
        public val value: String
    ): Query
}

public fun Query.getQueryValue(): String = when (this) {
    Query.Empty -> ""
    is Query.Text -> value
}
