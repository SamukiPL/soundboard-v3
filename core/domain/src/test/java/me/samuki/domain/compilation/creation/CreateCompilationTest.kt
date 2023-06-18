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
import me.samuki.model.Playable

class CreateCompilationTest : BehaviorSpec({

    val mockCompilationRepository = mockk<CompilationRepository>()
    val testDispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()
    val createCompilation = CreateCompilation(mockCompilationRepository, testDispatcher)

    afterTest {
        clearAllMocks()
    }

    given("a CreateCompilation instance") {

        val compilationName = Name("My Compilation")
        val soundList = listOf(
            Playable.Sound(Id("sound1"), true),
            Playable.Sound(Id("sound2"), true),
            Playable.Sound(Id("sound3"), true)
        )
        val params = CreateCompilation.Params(compilationName, soundList)

        `when`("creating a compilation and compilationRepository.createCompilation() returns success") {
            coEvery {
                mockCompilationRepository.createCompilation(
                    compilationName,
                    soundList
                )
            } returns Result.success(NoAnswer)

            then("it should return Result.Success with NoAnswer") {
                val result = withContext(testDispatcher) {
                    createCompilation(params)
                }
                result shouldBe Result.success(NoAnswer)
            }
        }

        `when`("creating a compilation and compilationRepository.createCompilation() returns failure") {
            val failure = Result.failure<NoAnswer>(Error())
            coEvery {
                mockCompilationRepository.createCompilation(
                    compilationName,
                    soundList
                )
            } returns failure

            then("it should return Result.Failure with the specified failure") {
                val result = withContext(testDispatcher) {
                    createCompilation(params)
                }
                result shouldBe failure
            }
        }

        `when`("creating a compilation and compilationRepository.createCompilation() throws an exception") {
            val exception = RuntimeException("An exception occurs")
            coEvery {
                mockCompilationRepository.createCompilation(
                    compilationName,
                    soundList
                )
            } throws exception

            then("it should throw an exception") {
                shouldThrow<RuntimeException> {
                    withContext(testDispatcher) {
                        createCompilation(params)
                    }
                } shouldBe exception
            }
        }
    }
})
