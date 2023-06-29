package me.samuki.resource.player

import android.content.Context
import android.media.MediaPlayer
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import me.samuki.common.di.DispatcherIO
import me.samuki.model.Combinable
import me.samuki.model.Compilation
import me.samuki.model.Pause
import me.samuki.model.Sound
import javax.inject.Singleton

@Singleton
internal class AndroidMediaPlayer(
    @ApplicationContext private val context: Context,
    @DispatcherIO private val coroutinesDispatcher: CoroutineDispatcher//TODO clean Coroutines
) : Player {

    private val playerScope = CoroutineScope(coroutinesDispatcher)

    private val mediaPlayer = MediaPlayer().apply {
        setOnPreparedListener { start() }
    }

    private val completionFlow: Flow<ReadyToPlay> = channelFlow {
        trySend(ReadyToPlay)
        mediaPlayer.setOnCompletionListener {
            it.reset()
            trySend(ReadyToPlay)
        }
    }

    private val combinableFlow = MutableSharedFlow<Combinable>()

    private lateinit var playingJob: Job

    init {
        setupPlayingJob()
    }

    override suspend fun stopPlaying() {
        playingJob.cancel()
        combinableFlow.resetReplayCache()
        setupPlayingJob()
    }

    override suspend fun playSound(sound: Sound) {
        combinableFlow.emit(sound)
    }

    override suspend fun playCompilation(compilation: Compilation) {
        combinableFlow.emitAll(compilation.sounds.asFlow())
    }

    private fun setupPlayingJob() {
        playingJob = playerScope.launch {
            completionFlow.zip(combinableFlow) { _, combinable ->
                prepareForPlay()
                when (combinable) {
                    is Pause -> delayForPause(combinable)
                    is Sound -> startPlayingSound(combinable)
                }
            }
        }
    }

    private fun prepareForPlay() {
        mediaPlayer.stop()
        mediaPlayer.reset()
    }

    private fun startPlayingSound(sound: Sound) {
        with(mediaPlayer) {
            setDataSource(context, sound.path.value)
            prepareAsync()
        }
    }

    private suspend fun delayForPause(pause: Pause) {
        delay(pause.duration)
    }
}
