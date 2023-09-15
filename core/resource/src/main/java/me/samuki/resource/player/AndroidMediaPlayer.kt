package me.samuki.resource.player

import android.content.Context
import android.media.MediaPlayer
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.zip
import me.samuki.common.di.DispatcherIO
import me.samuki.model.Combinable
import me.samuki.model.Compilation
import me.samuki.model.Pause
import me.samuki.model.Sound
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.time.Duration.Companion.milliseconds

@Singleton
internal class AndroidMediaPlayer @Inject constructor(
    @ApplicationContext private val context: Context,
    @DispatcherIO private val coroutinesDispatcher: CoroutineDispatcher//TODO clean Coroutines
) : Player {

    private val playerScope = CoroutineScope(coroutinesDispatcher)

    private val mediaPlayer = MediaPlayer().apply {
        setOnPreparedListener { start() }
    }

    private val completionFlow: Flow<ReadyToPlay> = callbackFlow {
        trySend(ReadyToPlay)
        mediaPlayer.setOnCompletionListener {
            it.reset()
            trySend(ReadyToPlay)
        }
        awaitClose()
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
        playingJob = completionFlow.zip(combinableFlow) { _, combinable ->
            prepareForPlay()
            when (combinable) {
                is Pause -> delayForPause(combinable)
                is Sound -> startPlayingSound(combinable)
            }
        }.launchIn(playerScope)
    }

    private fun prepareForPlay() {
        mediaPlayer.stop()
        mediaPlayer.reset()
    }

    private fun startPlayingSound(sound: Sound) = runCatching {
        with(mediaPlayer) {
            setDataSource(context, sound.path.value)
            prepareAsync()
        }
    }

    private suspend fun delayForPause(pause: Pause) {
        delay((pause.repeats * repeatsMultiplier).milliseconds)
    }

    private companion object {
        const val repeatsMultiplier = 100
    }
}
