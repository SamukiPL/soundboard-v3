package me.samuki.domain.sound.observe

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import me.samuki.domain.sound.SoundRepository
import me.samuki.model.NoAnswer

class ClearSoundsSearchTest : BehaviorSpec({
    val mockSoundRepository = mockk<SoundRepository>()
    val testDispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()
    val clearSoundsSearch = ClearSoundsSearch(mockSoundRepository, testDispatcher)

    afterTest {
        clearAllMocks()
    }

    given("a ClearSoundsSearch instance") {
        `when`("invoking clearSoundsSearch and soundRepository returns a successful result") {
            coEvery { mockSoundRepository.clearSearch() } returns Result.success(NoAnswer)

            val result = clearSoundsSearch()

            then("it should call clearSearch method of SoundRepository") {
                coVerify(exactly = 1) { mockSoundRepository.clearSearch() }
            }

            then("it should return a successful result") {
                result shouldBe Result.success(NoAnswer)
            }
        }

        `when`("invoking clearSoundsSearch and soundRepository returns a failure result") {
            val error = Error("Something went wrong")
            coEvery { mockSoundRepository.clearSearch() } returns Result.failure(error)

            val result = clearSoundsSearch()

            then("it should call clearSearch method of SoundRepository") {
                coVerify(exactly = 1) { mockSoundRepository.clearSearch() }
            }

            then("it should return a failure result") {
                result shouldBe Result.failure(error)
            }
        }

        `when`("invoking clearSoundsSearch and soundRepository throws an exception") {
            val exception = RuntimeException("An exception occurred")
            coEvery { mockSoundRepository.clearSearch() } throws exception

            val resultException = shouldThrow<RuntimeException> {
                clearSoundsSearch()
            }

            then("it should throw the exception") {
                resultException shouldBe exception
            }
        }
    }
})
