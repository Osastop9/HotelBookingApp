package com.decagon.hbapplicationgroupa.dependencyinjection.paystack

import com.decagon.hbapplicationgroupa.network.PaystackApiInterface
import com.decagon.hbapplicationgroupa.utils.BASE_URL_PAYSTACK
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PaystackRetrofitModule {

    @Singleton
    @Provides
    fun provideRetrofitInstance2(): Retrofit {
        //okhttp interceptor is used to to log retrofit responses especially when debugging.
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        return Retrofit.Builder()
            .baseUrl(BASE_URL_PAYSTACK)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().addInterceptor(interceptor).build())
            .build()
    }

    @Singleton
    @Provides
    fun providePaystackApi(): PaystackApiInterface {
        return provideRetrofitInstance2().create(PaystackApiInterface::class.java)
    }
}