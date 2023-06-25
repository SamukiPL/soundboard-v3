package me.samuki.domain.compilation.creation

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.withContext
import me.samuki.domain.compilation.CompilationRepository
import me.samuki.model.values.Id
import me.samuki.model.NoAnswer

class DeleteCompilationTest : BehaviorSpec({

    val mockCompilationRepository = mockk<CompilationRepository>()
    val testDispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()
    val deleteCompilation = DeleteCompilation(mockCompilationRepository, testDispatcher)

    afterTest {
        clearAllMocks()
    }

    given("a DeleteCompilation instance") {

        val compilationId = Id(1)

        `when`("deleting the compilation and compilationRepository.deleteCompilation() returns success") {
            coEvery { mockCompilationRepository.deleteCompilation(compilationId) } returns Result.success(
                NoAnswer
            )

            then("it should return Result.Success with NoAnswer") {
                val result = withContext(testDispatcher) {
                    deleteCompilation(compilationId)
                }
                result shouldBe Result.success(NoAnswer)
            }
        }

        `when`("deleting the compilation and compilationRepository.deleteCompilation() returns failure") {
            val failure = Result.failure<NoAnswer>(Error())
            coEvery { mockCompilationRepository.deleteCompilation(compilationId) } returns failure

            then("it should return Result.Failure with the specified failure") {
                val result = withContext(testDispatcher) {
                    deleteCompilation(compilationId)
                }
                result shouldBe failure
            }
        }

        `when`("deleting the compilation and compilationRepository.deleteCompilation() throws an exception") {
            val exception = RuntimeException("An exception occurs")
            coEvery { mockCompilationRepository.deleteCompilation(compilationId) } throws exception

            then("it should throw an exception") {
                shouldThrow<RuntimeException> {
                    withContext(testDispatcher) {
                        deleteCompilation(compilationId)
                    }
                } shouldBe exception
            }
        }
    }
})
