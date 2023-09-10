package me.samuki.model.values


public sealed interface Query {

    public data object Empty : Query

    @JvmInline
    public value class Text(
        public val value: String
    ): Query
}
