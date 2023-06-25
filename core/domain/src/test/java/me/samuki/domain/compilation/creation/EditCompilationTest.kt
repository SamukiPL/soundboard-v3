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
import me.samuki.model.values.Name
import me.samuki.model.NoAnswer
import me.samuki.model.Playable.Sound

class EditCompilationTest : BehaviorSpec({

    val mockCompilationRepository = mockk<CompilationRepository>()
    val testDispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()
    val editCompilation = EditCompilation(mockCompilationRepository, testDispatcher)

    afterTest {
        clearAllMocks()
    }

    given("an EditCompilation instance") {

        val compilationId = Id(1)
        val compilationName = Name("Compilation 123")
        val soundList = listOf(
            Sound(Id(1), true),
            Sound(Id(2), true),
            Sound(Id(3), true)
        )
        val params = EditCompilation.Params(compilationId, compilationName, soundList)

        `when`("editing the compilation and compilationRepository.editCompilation() returns success") {
            coEvery {
                mockCompilationRepository.editCompilation(
                    compilationId,
                    compilationName,
                    soundList
                )
            } returns Result.success(NoAnswer)

            then("it should return Result.Success with NoAnswer") {
                val result = withContext(testDispatcher) {
                    editCompilation(params)
                }
                result shouldBe Result.success(NoAnswer)
            }
        }

        `when`("editing the compilation and compilationRepository.editCompilation() returns failure") {
            val error = Error()
            coEvery {
                mockCompilationRepository.editCompilation(
                    compilationId,
                    compilationName,
                    soundList
                )
            } returns Result.failure(error)

            then("it should return Result.Failure with the specified failure") {
                val result = withContext(testDispatcher) {
                    editCompilation(params)
                }
                result shouldBe Result.failure(error)
            }
        }

        `when`("editing the compilation and compilationRepository.editCompilation() throws an exception") {
            val exception = RuntimeException("An exception occurs")
            coEvery {
                mockCompilationRepository.editCompilation(
                    compilationId,
                    compilationName,
                    soundList
                )
            } throws exception

            then("it should throw an exception") {
                shouldThrow<RuntimeException> {
                    withContext(testDispatcher) {
                        editCompilation(params)
                    }
                } shouldBe exception
            }
        }
    }
})
