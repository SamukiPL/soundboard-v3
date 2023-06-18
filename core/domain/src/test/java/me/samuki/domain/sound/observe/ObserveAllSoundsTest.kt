package me.samuki.domain.sound.observe

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import me.samuki.domain.sound.SoundRepository
import me.samuki.model.Playable.Sound

class ObserveAllSoundsTest : BehaviorSpec({
    val mockSoundRepository = mockk<SoundRepository>()
    val testDispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()
    val observeAllSounds = ObserveAllSounds(mockSoundRepository, testDispatcher)

    afterTest {
        clearAllMocks()
    }

    given("an ObserveAllSounds instance") {
        val mockSounds: List<Sound> = listOf()

        `when`("invoking observeAllSounds and soundRepository returns a flow of sounds") {
            coEvery { mockSoundRepository.observeSounds() } returns flowOf(mockSounds)

            then("mockSounds is collected from the flow") {
                observeAllSounds()
                    .collect {
                        it shouldBe mockSounds
                    }
            }
        }

        `when`("invoking observeAllSounds and soundRepository returns a flow that throws an Error") {
            coEvery { mockSoundRepository.observeSounds() } returns flow { throw Error() }

            then("the error is caught from the flow") {
                observeAllSounds()
                    .catch {
                        it shouldBe Error()
                    }
                    .collect()
            }
        }

        `when`("invoking observeAllSounds and soundRepository throws an Exception") {
            val exception = RuntimeException("An exception occurs")
            coEvery { mockSoundRepository.observeSounds() } throws exception

            then("it should throw an exception") {
                shouldThrow<RuntimeException> {
                    observeAllSounds()
                        .collect()
                } shouldBe exception
            }
        }
    }
})
