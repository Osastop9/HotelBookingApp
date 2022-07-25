package com.decagon.hbapplicationgroupa.application

import android.app.Application
import co.paystack.android.PaystackSdk
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HotelApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        // Initialize Paystack
        PaystackSdk.initialize(applicationContext)
    }
}
