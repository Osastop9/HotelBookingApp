package com.decagon.hbapplicationgroupa.dependencyinjection.paystack

import com.decagon.hbapplicationgroupa.network.PaystackApiInterface
import com.decagon.hbapplicationgroupa.repository.paystackrepository.PaystackRepository
import com.decagon.hbapplicationgroupa.repository.paystackrepository.PaystackRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PaystackRepositoryModule {

    @Singleton
    @Provides
    fun providePaystackRepository(paystackApiInterface: PaystackApiInterface): PaystackRepositoryInterface {
        return PaystackRepository(paystackApiInterface)
    }

}