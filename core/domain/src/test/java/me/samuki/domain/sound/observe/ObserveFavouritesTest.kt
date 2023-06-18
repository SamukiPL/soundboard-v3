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

class ObserveFavouritesTest : BehaviorSpec({
    val mockSoundRepository = mockk<SoundRepository>()
    val testDispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()
    val observeFavourites = ObserveFavourites(mockSoundRepository, testDispatcher)

    afterTest {
        clearAllMocks()
    }

    given("an ObserveFavourites instance") {
        val mockFavourites: List<Sound> = listOf()

        `when`("invoking observeFavourites and soundRepository returns a flow of favourites") {
            coEvery { mockSoundRepository.observeFavourites() } returns flowOf(mockFavourites)

            then("mockFavourites is collect from flow") {
                observeFavourites()
                    .collect {
                        it shouldBe mockFavourites
                    }
            }
        }

        `when`("invoking observeFavourites and soundRepository returns a flow that throws Error") {
            coEvery { mockSoundRepository.observeFavourites() } returns flow { throw Error() }

            then("error is caught from flow") {
                observeFavourites()
                    .catch {
                        it shouldBe Error()
                    }
                    .collect()
            }
        }

        `when`("invoking observeFavourites and soundRepository throws an Exception") {
            val exception = RuntimeException("An exception occurs")
            coEvery { mockSoundRepository.observeFavourites() } throws exception

            then("it should throw an exception") {
                shouldThrow<RuntimeException> {
                    observeFavourites()
                        .collect()
                } shouldBe exception
            }
        }
    }
})
