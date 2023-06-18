package me.samuki.domain.compilation.set

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
import me.samuki.model.values.Name
import me.samuki.model.Playable
import me.samuki.model.NoAnswer

class SetCompilationAsRingtoneTest : BehaviorSpec({
    val mockSetCompilationRepository = mockk<SetCompilationRepository>()
    val testDispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()
    val setCompilationAsRingtone = SetCompilationAsRingtone(mockSetCompilationRepository, testDispatcher)

    afterTest {
        clearAllMocks()
    }

    given("a SetCompilationAsRingtone instance") {
        val compilation = Playable.Compilation(
            id = Id("compilation123"),
            name = Name("Compilation Name"),
            sounds = listOf()
        )

        `when`("invoking setCompilationAsRingtone and setCompilationRepository returns success") {
            coEvery { mockSetCompilationRepository.setAsRingtone(compilation) } returns Result.success(NoAnswer)

            val result = setCompilationAsRingtone(compilation)

            then("it should call setAsRingtone method of SetCompilationRepository") {
                coVerify(exactly = 1) { mockSetCompilationRepository.setAsRingtone(compilation) }
            }

            then("it should return a success result") {
                result shouldBe Result.success(NoAnswer)
            }
        }

        `when`("invoking setCompilationAsRingtone and setCompilationRepository returns failure") {
            val error = Error("Something went wrong")
            coEvery { mockSetCompilationRepository.setAsRingtone(compilation) } returns Result.failure(error)

            val result = setCompilationAsRingtone(compilation)

            then("it should call setAsRingtone method of SetCompilationRepository") {
                coVerify(exactly = 1) { mockSetCompilationRepository.setAsRingtone(compilation) }
            }

            then("it should return a failure result") {
                result shouldBe Result.failure(error)
            }
        }

        `when`("invoking setCompilationAsRingtone and setCompilationRepository throws an exception") {
            val exception = RuntimeException("An exception occurred")
            coEvery { mockSetCompilationRepository.setAsRingtone(compilation) } throws exception

            val resultException = shouldThrow<RuntimeException> {
                setCompilationAsRingtone(compilation)
            }

            then("it should throw the exception") {
                resultException shouldBe exception
            }
        }
    }
})
