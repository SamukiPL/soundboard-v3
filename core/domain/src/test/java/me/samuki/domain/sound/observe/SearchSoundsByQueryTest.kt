package me.samuki.domain.sound.observe

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import me.samuki.domain.sound.SoundRepository
import me.samuki.model.NoAnswer
import me.samuki.model.values.Query

class SearchSoundsByQueryTest : BehaviorSpec({
    val mockSoundRepository = mockk<SoundRepository>()
    val testDispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()
    val searchSoundsByQuery = SearchSoundsByQuery(mockSoundRepository, testDispatcher)

    afterTest {
        clearAllMocks()
    }

    given("a SearchSoundsByQuery instance") {
        val query = Query("test")

        `when`("invoking searchSoundsByQuery and soundRepository returns a success result") {
            coEvery { mockSoundRepository.searchByQuery(query) } returns Result.success(NoAnswer)

            val result = searchSoundsByQuery(query)

            then("it should call searchByQuery method of SoundRepository") {
                coVerify(exactly = 1) { mockSoundRepository.searchByQuery(query) }
            }

            then("it should return a successful result") {
                result.isSuccess shouldBe true
                result.getOrNull() shouldBe NoAnswer
            }
        }

        `when`("invoking searchSoundsByQuery and soundRepository returns a failure result") {
            val error = Error("Something went wrong")
            coEvery { mockSoundRepository.searchByQuery(query) } returns Result.failure(error)

            val result = searchSoundsByQuery(query)

            then("it should call searchByQuery method of SoundRepository") {
                coVerify(exactly = 1) { mockSoundRepository.searchByQuery(query) }
            }

            then("it should return a failure result") {
                result.isFailure shouldBe true
                result.exceptionOrNull() shouldBe error
            }
        }

        `when`("invoking searchSoundsByQuery and soundRepository throws an exception") {
            val exception = RuntimeException("An exception occurred")
            coEvery { mockSoundRepository.searchByQuery(query) } throws exception

            then("it should throw an exception") {
                shouldThrow<RuntimeException> {
                    searchSoundsByQuery(query)
                } shouldBe exception
            }
        }
    }
})
