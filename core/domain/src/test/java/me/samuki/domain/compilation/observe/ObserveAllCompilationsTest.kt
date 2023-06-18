package me.samuki.domain.compilation.observe

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
import me.samuki.domain.compilation.CompilationRepository
import me.samuki.model.Playable

class ObserveAllCompilationsTest : BehaviorSpec({

    val mockCompilationRepository = mockk<CompilationRepository>()
    val testDispatcher: CoroutineDispatcher = UnconfinedTestDispatcher()
    val observeAllCompilations = ObserveAllCompilations(mockCompilationRepository, testDispatcher)

    afterTest {
        clearAllMocks()
    }

    given("an ObserveAllCompilations instance") {

        val mockCompilations: List<Playable.Compilation> = listOf(
            mockk(),
            mockk(),
            mockk()
        )

        `when`("invoking observeAllCompilations and compilationRepository returns a flow of compilations") {
            coEvery { mockCompilationRepository.observeCompilations() } returns flowOf(
                mockCompilations
            )

            then("mockCompilations is collected from the flow") {
                observeAllCompilations()
                    .collect {
                        it shouldBe mockCompilations
                    }
            }
        }

        `when`("invoking observeAllCompilations and compilationRepository returns a flow that throws an Error") {
            coEvery { mockCompilationRepository.observeCompilations() } returns flow { throw Error() }

            then("the error is caught from the flow") {
                observeAllCompilations()
                    .catch {
                        it shouldBe Error()
                    }
                    .collect()
            }
        }

        `when`("invoking observeAllCompilations and compilationRepository throws an Exception") {
            val exception = RuntimeException("An exception occurs")
            coEvery { mockCompilationRepository.observeCompilations() } throws exception

            then("it should throw an exception") {
                shouldThrow<RuntimeException> {
                    observeAllCompilations()
                        .collect()
                } shouldBe exception
            }
        }
    }
})
