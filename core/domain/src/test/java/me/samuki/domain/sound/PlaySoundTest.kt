package me.samuki.domain.sound

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import me.samuki.domain.PlayRepository
import me.samuki.model.values.Id
import me.samuki.model.NoAnswer
import me.samuki.model.Playable

class PlaySoundTest : BehaviorSpec({
    val mockPlayRepository = mockk<PlayRepository>()
    val testDispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()
    val playSound = PlaySound(mockPlayRepository, testDispatcher)

    afterTest {
        clearAllMocks()
    }

    given("a PlaySound instance") {
        val sound = Playable.Sound(Id("sound123"), false)

        `when`("invoking playSound and playRepository returns success") {
            coEvery { mockPlayRepository.playSound(sound) } returns Result.success(NoAnswer)

            val result = playSound(sound)

            then("it should call playSound method of PlayRepository") {
                coVerify(exactly = 1) { mockPlayRepository.playSound(sound) }
            }

            then("it should return a successful result") {
                result shouldBe Result.success(NoAnswer)
            }
        }

        `when`("invoking playSound and playRepository returns failure") {
            val error = Error("Something went wrong")
            coEvery { mockPlayRepository.playSound(sound) } returns Result.failure(error)

            val result = playSound(sound)

            then("it should call playSound method of PlayRepository") {
                coVerify(exactly = 1) { mockPlayRepository.playSound(sound) }
            }

            then("it should return a failure result") {
                result shouldBe Result.failure(error)
            }
        }

        `when`("invoking playSound and playRepository throws an exception") {
            val exception = RuntimeException("An exception occurred")
            coEvery { mockPlayRepository.playSound(sound) } throws exception

            val resultException = shouldThrow<RuntimeException> {
                playSound(sound)
            }

            then("it should throw the exception") {
                resultException shouldBe exception
            }
        }
    }
})
