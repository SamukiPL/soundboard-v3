package me.samuki.resource.thankyou

public interface ThankYouProvider {
    public suspend fun shouldShowThankYouAndMarkAsShown(): Boolean
}
