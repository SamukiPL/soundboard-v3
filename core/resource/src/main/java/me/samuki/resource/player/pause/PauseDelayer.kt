package me.samuki.resource.player.pause

import me.samuki.model.Pause

internal typealias OnDelayFinishedListener = () -> Unit

internal interface PauseDelayer {

    suspend fun delayForPause(pause: Pause)

    fun setOnDelayFinishedListener(onDelayFinished: OnDelayFinishedListener)
}
