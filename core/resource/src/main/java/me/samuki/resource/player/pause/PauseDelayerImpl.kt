package me.samuki.resource.player.pause

import io.github.aakira.napier.Napier
import kotlinx.coroutines.delay
import me.samuki.model.Pause
import javax.inject.Inject
import kotlin.time.Duration.Companion.milliseconds

internal class PauseDelayerImpl @Inject constructor() : PauseDelayer {

    private var onDelayFinished: OnDelayFinishedListener? = null

    override suspend fun delayForPause(pause: Pause) {
        delay((pause.repeats * REPEATS_MULTIPLIER).milliseconds)
        onDelayFinished?.invoke() ?: run {
            Napier.e("onDelayFinished should not be null, can't run compilation further")
        }
    }

    override fun setOnDelayFinishedListener(onDelayFinished: OnDelayFinishedListener) {
        this.onDelayFinished = onDelayFinished
    }

    private companion object {
        const val REPEATS_MULTIPLIER = 100
    }
}
