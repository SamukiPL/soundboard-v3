package me.samuki.domain.sound.set

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import me.samuki.model.values.Id
import me.samuki.model.NoAnswer
import me.samuki.model.Playable
import java.lang.RuntimeException

class SetSoundAsNotificationTest : BehaviorSpec({
    val mockSetSoundRepository = mockk<SetSoundRepository>()
    val testDispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()
    val setSoundAsNotification = SetSoundAsNotification(mockSetSoundRepository, testDispatcher)

    afterTest {
        clearAllMocks()
    }

    given("a SetSoundAsNotification instance") {
        val sound = Playable.Sound(Id("sound123"), false)

        `when`("invoking setSoundAsNotification and setSoundRepository returns success") {
            coEvery { mockSetSoundRepository.setAsNotification(sound) } returns Result.success(NoAnswer)

            val result = setSoundAsNotification(sound)

            then("it should call setAsNotification method of SetSoundRepository") {
                coVerify(exactly = 1) { mockSetSoundRepository.setAsNotification(sound) }
            }

            then("it should return a successful result") {
                result shouldBe Result.success(NoAnswer)
            }
        }

        `when`("invoking setSoundAsNotification and setSoundRepository returns failure") {
            val error = RuntimeException("An error occurred")
            coEvery { mockSetSoundRepository.setAsNotification(sound) } returns Result.failure(error)

            val result = setSoundAsNotification(sound)

            then("it should call setAsNotification method of SetSoundRepository") {
                coVerify(exactly = 1) { mockSetSoundRepository.setAsNotification(sound) }
            }

            then("it should return a failure result") {
                result shouldBe Result.failure(error)
            }
        }

        `when`("invoking setSoundAsNotification and setSoundRepository throws an exception") {
            val exception = RuntimeException("An exception occurred")
            coEvery { mockSetSoundRepository.setAsNotification(sound) } throws exception

            val resultException = shouldThrow<RuntimeException> {
                setSoundAsNotification(sound)
            }

            then("it should throw the exception") {
                resultException shouldBe exception
            }
        }
    }
})
