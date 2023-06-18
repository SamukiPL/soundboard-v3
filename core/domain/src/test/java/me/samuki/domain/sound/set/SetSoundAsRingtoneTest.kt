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

class SetSoundAsRingtoneTest : BehaviorSpec({
    val mockSetSoundRepository = mockk<SetSoundRepository>()
    val testDispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()
    val setSoundAsRingtone = SetSoundAsRingtone(mockSetSoundRepository, testDispatcher)

    afterTest {
        clearAllMocks()
    }

    given("a SetSoundAsRingtone instance") {
        val sound = Playable.Sound(Id("sound123"), false)

        `when`("invoking setSoundAsRingtone and setSoundRepository returns success") {
            coEvery { mockSetSoundRepository.setAsRingtone(sound) } returns Result.success(NoAnswer)

            val result = setSoundAsRingtone(sound)

            then("it should call setAsRingtone method of SetSoundRepository") {
                coVerify(exactly = 1) { mockSetSoundRepository.setAsRingtone(sound) }
            }

            then("it should return a successful result") {
                result shouldBe Result.success(NoAnswer)
            }
        }

        `when`("invoking setSoundAsRingtone and setSoundRepository returns failure") {
            val error = RuntimeException("An error occurred")
            coEvery { mockSetSoundRepository.setAsRingtone(sound) } returns Result.failure(error)

            val result = setSoundAsRingtone(sound)

            then("it should call setAsRingtone method of SetSoundRepository") {
                coVerify(exactly = 1) { mockSetSoundRepository.setAsRingtone(sound) }
            }

            then("it should return a failure result") {
                result shouldBe Result.failure(error)
            }
        }

        `when`("invoking setSoundAsRingtone and setSoundRepository throws an exception") {
            val exception = RuntimeException("An exception occurred")
            coEvery { mockSetSoundRepository.setAsRingtone(sound) } throws exception

            val resultException = shouldThrow<RuntimeException> {
                setSoundAsRingtone(sound)
            }

            then("it should throw the exception") {
                resultException shouldBe exception
            }
        }
    }
})
