package me.samuki.navigation.graph

import com.ramcosta.composedestinations.animations.defaults.DefaultFadingTransitions
import com.ramcosta.composedestinations.annotation.NavHostGraph
import com.ramcosta.composedestinations.annotation.parameters.CodeGenVisibility

@NavHostGraph(
    defaultTransitions = DefaultFadingTransitions::class,
    visibility = CodeGenVisibility.INTERNAL
)
internal annotation class SoundboardGraph
