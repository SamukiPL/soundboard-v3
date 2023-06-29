package me.samuki.model

import kotlin.time.Duration

@JvmInline
value class Pause(
    val duration: Duration
): Combinable
