package me.samuki.domain.sound

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import me.samuki.domain.ShareRepository
import me.samuki.model.values.Id
import me.samuki.model.NoAnswer
import me.samuki.model.Playable
import java.lang.Error

class ShareSoundTest : BehaviorSpec({
    val mockShareRepository = mockk<ShareRepository>()
    val testDispatcher = UnconfinedTestDispatcher()
    val shareSound = ShareSound(mockShareRepository, testDispatcher)

    afterTest {
        clearAllMocks()
    }

    given("a ShareSound instance") {
        val sound = Playable.Sound(Id(1), false)

        `when`("invoking shareSound and shareRepository returns success") {
            coEvery { mockShareRepository.shareSound(sound) } returns Result.success(NoAnswer)

            val result = shareSound(sound)

            then("it should call shareSound method of ShareRepository") {
                coVerify(exactly = 1) { mockShareRepository.shareSound(sound) }
            }

            then("it should return a successful result") {
                result shouldBe Result.success(NoAnswer)
            }
        }

        `when`("invoking shareSound and shareRepository returns failure") {
            val error = Error("Something went wrong")
            coEvery { mockShareRepository.shareSound(sound) } returns Result.failure(error)

            val result = shareSound(sound)

            then("it should call shareSound method of ShareRepository") {
                coVerify(exactly = 1) { mockShareRepository.shareSound(sound) }
            }

            then("it should return a failure result") {
                result shouldBe Result.failure(error)
            }
        }

        `when`("invoking shareSound and shareRepository throws an exception") {
            val exception = RuntimeException("An exception occurred")
            coEvery { mockShareRepository.shareSound(sound) } throws exception

            val resultException = shouldThrow<Exception> {
                shareSound(sound)
            }

            then("it should return exception") {
                resultException shouldBe exception
            }
        }
    }
})
