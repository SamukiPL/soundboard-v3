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
import me.samuki.domain.ShareRepository
import me.samuki.model.values.Id
import me.samuki.model.values.Name
import me.samuki.model.NoAnswer
import me.samuki.model.Playable

class ShareCompilationTest : BehaviorSpec({
    val mockShareRepository = mockk<ShareRepository>()
    val testDispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()
    val shareCompilation = ShareCompilation(mockShareRepository, testDispatcher)

    afterTest {
        clearAllMocks()
    }

    given("a ShareCompilation instance") {
        val compilation = Playable.Compilation(
            id = Id("compilation123"),
            name = Name("Compilation Name"),
            sounds = listOf()
        )

        `when`("invoking shareCompilation and shareRepository returns success") {
            coEvery { mockShareRepository.shareCompilation(compilation) } returns Result.success(
                NoAnswer
            )

            val result = shareCompilation(compilation)

            then("it should call shareCompilation method of ShareRepository") {
                coVerify(exactly = 1) { mockShareRepository.shareCompilation(compilation) }
            }

            then("it should return a success result") {
                result shouldBe Result.success(NoAnswer)
            }
        }

        `when`("invoking shareCompilation and shareRepository returns failure") {
            val error = Error("Something went wrong")
            coEvery { mockShareRepository.shareCompilation(compilation) } returns Result.failure(
                error
            )

            val result = shareCompilation(compilation)

            then("it should call shareCompilation method of ShareRepository") {
                coVerify(exactly = 1) { mockShareRepository.shareCompilation(compilation) }
            }

            then("it should return a failure result") {
                result shouldBe Result.failure(error)
            }
        }

        `when`("invoking shareCompilation and shareRepository throws an exception") {
            val exception = RuntimeException("An exception occurred")
            coEvery { mockShareRepository.shareCompilation(compilation) } throws exception

            val resultException = shouldThrow<RuntimeException> {
                shareCompilation(compilation)
            }

            then("it should throw the exception") {
                resultException shouldBe exception
            }
        }
    }
})
