package me.samuki.domain.playable

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import me.samuki.domain.compilation.ShareCompilation
import me.samuki.domain.sound.ShareSound
import me.samuki.model.values.Id
import me.samuki.model.values.Name
import me.samuki.model.NoAnswer
import me.samuki.model.Playable

class SharePlayableTest : BehaviorSpec({

    val mockShareSound = mockk<ShareSound>(relaxed = true)
    val mockShareCompilation = mockk<ShareCompilation>(relaxed = true)
    val testDispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()
    val sharePlayable = SharePlayable(mockShareSound, mockShareCompilation, testDispatcher)

    beforeSpec {
        Dispatchers.setMain(testDispatcher)
    }

    afterSpec {
        Dispatchers.resetMain()
    }

    afterTest {
        clearAllMocks()
    }

    given("a SharePlayable instance") {
        val sound = Playable.Sound(Id(""), true)
        val compilation = Playable.Compilation(Id(""), Name(""), emptyList())

        val coEveryCorrectMock = { playable: Playable ->
            coEvery {
                when (playable) {
                    is Playable.Compilation -> mockShareCompilation(compilation)
                    is Playable.Sound -> mockShareSound(sound)
                }
            }
        }

        listOf(sound, compilation).forAll {
            `when`("sharing a ${it::class.simpleName} and correct use case succeeds") {
                coEveryCorrectMock(it) returns Result.success(NoAnswer)

                sharePlayable(it) shouldBe Result.success(NoAnswer)

                then("it should call correct useCase method") {
                    coVerify(inverse = it !is Playable.Compilation) {
                        mockShareCompilation(
                            compilation
                        )
                    }
                    coVerify(inverse = it !is Playable.Sound) { mockShareSound(sound) }
                }
            }

            `when`("sharing a ${it::class.simpleName} and correct use case returns Failure") {
                val failureResult = Result.failure<NoAnswer>(Error())
                coEveryCorrectMock(it) returns failureResult

                sharePlayable(it) shouldBe failureResult

                then("it should call correct useCase method") {
                    coVerify(inverse = it !is Playable.Compilation) {
                        mockShareCompilation(
                            compilation
                        )
                    }
                    coVerify(inverse = it !is Playable.Sound) { mockShareSound(sound) }
                }
            }

            `when`("sharing a ${it::class.simpleName} and correct use case throws Exception") {
                val exception = RuntimeException("some exception")
                coEveryCorrectMock(it) throws exception

                then("it should throws exception") {
                    shouldThrow<Exception> {
                        sharePlayable(it)
                    } shouldBe exception
                }
            }
        }
    }
})
