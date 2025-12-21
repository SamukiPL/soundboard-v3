package me.samuki.domain.thankyou

public interface ThankYouRepository {
    public suspend fun shouldShowThankYouAndMarkAsShown(): Boolean
}