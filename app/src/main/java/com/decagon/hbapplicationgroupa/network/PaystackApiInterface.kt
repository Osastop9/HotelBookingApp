package com.decagon.hbapplicationgroupa.network

import com.decagon.hbapplicationgroupa.model.paystack.InitializeTransaction
import com.decagon.hbapplicationgroupa.model.paystack.InitializeTransactionResponse
import com.decagon.hbapplicationgroupa.model.paystack.VerifyTransactionResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface PaystackApiInterface {

    @POST("transaction/initialize")
    suspend fun getInitTransactionResponse(@Header("Authorization") payAuthToken: String, @Body paymentInfo: InitializeTransaction): Response<InitializeTransactionResponse>

    @GET("/transaction/verify/:reference")
    suspend fun verifyTransaction(): Response<VerifyTransactionResponse>
}