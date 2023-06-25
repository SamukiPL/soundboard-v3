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
import me.samuki.model.NoAnswer
import me.samuki.model.Playable

class SetCompilationAsNotificationTest : BehaviorSpec({
    val mockSetCompilationRepository = mockk<SetCompilationRepository>()
    val testDispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()
    val setCompilationAsNotification =
        SetCompilationAsNotification(mockSetCompilationRepository, testDispatcher)

    afterTest {
        clearAllMocks()
    }

    given("a SetCompilationAsNotification instance") {
        val compilation = Playable.Compilation(
            id = Id(1),
            name = Name("Compilation Name"),
            sounds = listOf()
        )

        `when`("invoking setCompilationAsNotification and setCompilationRepository returns success") {
            coEvery { mockSetCompilationRepository.setAsNotification(compilation) } returns Result.success(
                NoAnswer
            )

            val result = setCompilationAsNotification(compilation)

            then("it should call setAsNotification method of SetCompilationRepository") {
                coVerify(exactly = 1) { mockSetCompilationRepository.setAsNotification(compilation) }
            }

            then("it should return a success result") {
                result shouldBe Result.success(NoAnswer)
            }
        }

        `when`("invoking setCompilationAsNotification and setCompilationRepository returns failure") {
            val error = Error("Something went wrong")
            coEvery { mockSetCompilationRepository.setAsNotification(compilation) } returns Result.failure(
                error
            )

            val result = setCompilationAsNotification(compilation)

            then("it should call setAsNotification method of SetCompilationRepository") {
                coVerify(exactly = 1) { mockSetCompilationRepository.setAsNotification(compilation) }
            }

            then("it should return a failure result") {
                result shouldBe Result.failure(error)
            }
        }

        `when`("invoking setCompilationAsNotification and setCompilationRepository throws an exception") {
            val exception = RuntimeException("An exception occurred")
            coEvery { mockSetCompilationRepository.setAsNotification(compilation) } throws exception

            val resultException = shouldThrow<RuntimeException> {
                setCompilationAsNotification(compilation)
            }

            then("it should throw the exception") {
                resultException shouldBe exception
            }
        }
    }
})
