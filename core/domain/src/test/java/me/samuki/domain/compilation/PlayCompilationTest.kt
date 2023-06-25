package me.samuki.domain.compilation

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
import me.samuki.model.values.Name
import me.samuki.model.NoAnswer
import me.samuki.model.Playable

class PlayCompilationTest : BehaviorSpec({
    val mockPlayRepository = mockk<PlayRepository>()
    val testDispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()
    val playCompilation = PlayCompilation(mockPlayRepository, testDispatcher)

    afterTest {
        clearAllMocks()
    }

    given("a PlayCompilation instance") {
        val compilation = Playable.Compilation(
            id = Id(1),
            name = Name("Compilation Name"),
            sounds = listOf()
        )

        `when`("invoking playCompilation and playRepository returns success") {
            coEvery { mockPlayRepository.playCompilation(compilation) } returns Result.success(
                NoAnswer
            )

            val result = playCompilation(compilation)

            then("it should call playCompilation method of PlayRepository") {
                coVerify(exactly = 1) { mockPlayRepository.playCompilation(compilation) }
            }

            then("it should return a success result") {
                result shouldBe Result.success(NoAnswer)
            }
        }

        `when`("invoking playCompilation and playRepository returns failure") {
            val error = Error("Something went wrong")
            coEvery { mockPlayRepository.playCompilation(compilation) } returns Result.failure(error)

            val result = playCompilation(compilation)

            then("it should call playCompilation method of PlayRepository") {
                coVerify(exactly = 1) { mockPlayRepository.playCompilation(compilation) }
            }

            then("it should return a failure result") {
                result shouldBe Result.failure(error)
            }
        }

        `when`("invoking playCompilation and playRepository throws an exception") {
            val exception = RuntimeException("An exception occurred")
            coEvery { mockPlayRepository.playCompilation(compilation) } throws exception

            val resultException = shouldThrow<RuntimeException> {
                playCompilation(compilation)
            }

            then("it should throw the exception") {
                resultException shouldBe exception
            }
        }
    }
})
