package me.samuki.domain.compilation.observe

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import me.samuki.domain.compilation.CompilationRepository
import me.samuki.model.NoAnswer
import me.samuki.model.values.Query

class SearchCompilationsByQueryTest : BehaviorSpec({

    val mockCompilationRepository = mockk<CompilationRepository>()
    val testDispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()
    val searchCompilationsByQuery =
        SearchCompilationsByQuery(mockCompilationRepository, testDispatcher)

    afterTest {
        clearAllMocks()
    }

    given("a SearchCompilationsByQuery instance") {

        val query = Query("example query")

        `when`("invoking searchCompilationsByQuery and compilationRepository returns Result<NoAnswer>") {
            coEvery { mockCompilationRepository.searchByQuery(query) } returns Result.success(
                NoAnswer
            )

            then("Result<NoAnswer> should be returned") {
                val result = searchCompilationsByQuery(query)
                result shouldBe Result.success(NoAnswer)
            }
        }

        `when`("invoking searchCompilationsByQuery and compilationRepository returns a failure") {
            val error = Error()
            coEvery { mockCompilationRepository.searchByQuery(query) } returns Result.failure(error)

            then("Result<NoAnswer> should be returned with the failure") {
                val result = searchCompilationsByQuery(query)
                result shouldBe Result.failure(error)
            }
        }

        `when`("invoking searchCompilationsByQuery and compilationRepository throws an exception") {
            val exception = RuntimeException("An exception occurred")
            coEvery { mockCompilationRepository.searchByQuery(query) } throws exception

            then("it should throw an exception") {
                shouldThrow<RuntimeException> {
                    searchCompilationsByQuery(query)
                } shouldBe exception
            }
        }
    }
})
