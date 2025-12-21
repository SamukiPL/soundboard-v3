package me.samuki.domain.thankyou

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import me.samuki.common.di.DispatcherIO
import javax.inject.Inject

public class ShouldShowThankYou @Inject constructor(
    private val thankYouRepository: ThankYouRepository,
    @param:DispatcherIO private val coroutineDispatcher: CoroutineDispatcher
) {
    public suspend operator fun invoke(): Boolean = withContext(coroutineDispatcher) {
        thankYouRepository.shouldShowThankYouAndMarkAsShown()
    }
}
