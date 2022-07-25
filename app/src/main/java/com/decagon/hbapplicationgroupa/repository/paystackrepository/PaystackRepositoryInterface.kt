package com.decagon.hbapplicationgroupa.repository.paystackrepository

import com.decagon.hbapplicationgroupa.model.paystack.InitializeTransaction
import com.decagon.hbapplicationgroupa.model.paystack.InitializeTransactionResponse
import retrofit2.Response

interface PaystackRepositoryInterface {

    suspend fun initializeTransactionResponse(payAuthToken: String, paymentInfo: InitializeTransaction): Response<InitializeTransactionResponse>

}