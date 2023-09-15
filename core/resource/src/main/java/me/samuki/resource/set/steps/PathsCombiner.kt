package me.samuki.resource.set.steps

import me.samuki.model.Combinable
import me.samuki.model.Pause
import me.samuki.model.Sound
import me.samuki.model.values.Path
import me.samuki.resource.di.SilencePath
import javax.inject.Inject

internal class PathsCombiner @Inject constructor(
    @SilencePath private val path: Path
) {

    fun combinePaths(combinable: Array<out Combinable>): List<Path> = combinable.flatMap {
        when (it) {
            is Pause -> combinePause(it)
            is Sound -> listOf(it.path)
        }
    }

    private fun combinePause(pause: Pause): List<Path> = List(pause.repeats) {
        path
    }
}
