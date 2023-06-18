package me.samuki.domain.compilation.set

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import me.samuki.domain.params.SetType
import me.samuki.model.values.Id
import me.samuki.model.values.Name
import me.samuki.model.NoAnswer
import me.samuki.model.Playable

class SetCompilationTest : BehaviorSpec({
    val mockSetCompilationAsNotification = mockk<SetCompilationAsNotification>()
    val mockSetCompilationAsRingtone = mockk<SetCompilationAsRingtone>()
    val testDispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()
    val setCompilation =
        SetCompilation(
            mockSetCompilationAsNotification,
            mockSetCompilationAsRingtone,
            testDispatcher
        )

    afterTest {
        clearAllMocks()
    }

    given("a SetCompilation instance") {
        val compilation = Playable.Compilation(
            id = Id("compilation123"),
            name = Name("Compilation Name"),
            sounds = listOf()
        )

        val coEveryCorrectMock = { type: SetType ->
            coEvery {
                when (type) {
                    SetType.Notification -> mockSetCompilationAsNotification(compilation)
                    SetType.Ringtone -> mockSetCompilationAsRingtone(compilation)
                }
            }
        }

        SetType.values().forAll { type ->
            `when`("invoking setCompilation with ${type.name}") {
                coEveryCorrectMock(type) returns Result.success(
                    NoAnswer
                )
                val result = setCompilation(compilation, type)

                then("it should call useCase method") {
                    coVerify(inverse = SetType.Notification != type) { mockSetCompilationAsNotification(compilation) }
                    coVerify(inverse = SetType.Ringtone != type) { mockSetCompilationAsRingtone(compilation) }
                }

                then("it should return a successful result") {
                    result shouldBe Result.success(NoAnswer)
                }
            }

            `when`("invoking setCompilation with ${type.name} and setCompilationAsNotification returns a failure") {
                val failureResult = Result.failure<NoAnswer>(Error())
                coEveryCorrectMock(type) returns failureResult

                val result = setCompilation(compilation, type)

                then("it should call useCase method") {
                    coVerify(inverse = SetType.Notification != type) { mockSetCompilationAsNotification(compilation) }
                    coVerify(inverse = SetType.Ringtone != type) { mockSetCompilationAsRingtone(compilation) }
                }

                then("it should return the failure result") {
                    result shouldBe failureResult
                }
            }

            `when`("invoking setCompilation with ${type.name} and setCompilationAsNotification throws an exception") {
                val exception = RuntimeException("Some exception")
                coEveryCorrectMock(type) throws exception

                then("it should throw the exception") {
                    shouldThrow<Exception> {
                        setCompilation(compilation, type)
                    } shouldBe exception
                }
            }
        }
    }
})
