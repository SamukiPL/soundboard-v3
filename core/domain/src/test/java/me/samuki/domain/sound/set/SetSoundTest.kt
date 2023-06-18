package me.samuki.domain.sound.set

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
import me.samuki.model.NoAnswer
import me.samuki.model.Playable
import java.lang.RuntimeException

class SetSoundTest : BehaviorSpec({
    val mockSetSoundAsNotification = mockk<SetSoundAsNotification>()
    val mockSetSoundAsRingtone = mockk<SetSoundAsRingtone>()
    val testDispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()
    val setSound = SetSound(mockSetSoundAsNotification, mockSetSoundAsRingtone, testDispatcher)

    afterTest {
        clearAllMocks()
    }

    given("a SetSound instance") {
        val sound = Playable.Sound(Id(""), false)

        val coEveryCorrectMock = { type: SetType ->
            coEvery {
                when (type) {
                    SetType.Notification -> mockSetSoundAsNotification(sound)
                    SetType.Ringtone -> mockSetSoundAsRingtone(sound)
                }
            }
        }

        SetType.values().forAll { type ->
            `when`("invoking setSound with ${type.name}") {
                coEveryCorrectMock(type) returns Result.success(
                    NoAnswer
                )
                val result = setSound(sound, type)

                then("it should call useCase method") {
                    coVerify(inverse = SetType.Notification != type) { mockSetSoundAsNotification(sound) }
                    coVerify(inverse = SetType.Ringtone != type) { mockSetSoundAsRingtone(sound) }
                }

                then("it should return a successful result") {
                    result shouldBe Result.success(NoAnswer)
                }
            }

            `when`("invoking setSound with ${type.name} and setSoundAsNotification returns a failure") {
                val failureResult = Result.failure<NoAnswer>(Error())
                coEveryCorrectMock(type) returns failureResult

                val result = setSound(sound, type)

                then("it should call useCase method") {
                    coVerify(inverse = SetType.Notification != type) { mockSetSoundAsNotification(sound) }
                    coVerify(inverse = SetType.Ringtone != type) { mockSetSoundAsRingtone(sound) }
                }

                then("it should return the failure result") {
                    result shouldBe failureResult
                }
            }

            `when`("invoking setSound with ${type.name} and setSoundAsNotification throws an exception") {
                val exception = RuntimeException("Some exception")
                coEveryCorrectMock(type) throws exception

                then("it should throw the exception") {
                    shouldThrow<Exception> {
                        setSound(sound, type)
                    } shouldBe exception
                }
            }
        }
    }
})
