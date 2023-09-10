package me.samuki.model

import kotlin.time.Duration

@JvmInline
public value class Pause(
    public val duration: Duration
): Combinable
