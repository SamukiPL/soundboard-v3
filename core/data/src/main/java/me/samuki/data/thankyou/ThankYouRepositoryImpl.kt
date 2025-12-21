package me.samuki.data.thankyou

import me.samuki.domain.thankyou.ThankYouRepository
import me.samuki.resource.thankyou.ThankYouProvider
import javax.inject.Inject

internal class ThankYouRepositoryImpl @Inject constructor(
    private val thankYouProvider: ThankYouProvider
) : ThankYouRepository {

    override suspend fun shouldShowThankYouAndMarkAsShown(): Boolean =
        thankYouProvider.shouldShowThankYouAndMarkAsShown()
}
