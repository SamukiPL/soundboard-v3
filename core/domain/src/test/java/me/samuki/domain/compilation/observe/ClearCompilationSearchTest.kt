package me.samuki.domain.compilation.observe

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import me.samuki.domain.compilation.CompilationRepository
import me.samuki.model.NoAnswer

class ClearCompilationSearchTest : BehaviorSpec({

    val mockCompilationRepository = mockk<CompilationRepository>()
    val testDispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()
    val clearCompilationSearch = ClearCompilationSearch(mockCompilationRepository, testDispatcher)

    afterTest {
        clearAllMocks()
    }

    given("a ClearCompilationSearch instance") {

        `when`("invoking clearCompilationSearch and compilationRepository.clearSearch() returns success") {
            coEvery { mockCompilationRepository.clearSearch() } returns Result.success(NoAnswer)

            then("it should return Result.Success with NoAnswer") {
                val result = clearCompilationSearch()
                result shouldBe Result.success(NoAnswer)
            }
        }

        `when`("invoking clearCompilationSearch and compilationRepository.clearSearch() returns failure") {
            val error = Error()
            coEvery { mockCompilationRepository.clearSearch() } returns Result.failure<NoAnswer>(
                error
            )

            then("it should return Result.Failure with the specified failure") {
                val result = clearCompilationSearch()
                result shouldBe Result.failure(error)
            }
        }

        `when`("invoking clearCompilationSearch and compilationRepository.clearSearch() throws an exception") {
            val exception = RuntimeException("An exception occurs")
            coEvery { mockCompilationRepository.clearSearch() } throws exception

            then("it should throw an exception") {
                shouldThrow<RuntimeException> {
                    clearCompilationSearch()
                } shouldBe exception
            }
        }
    }
})
