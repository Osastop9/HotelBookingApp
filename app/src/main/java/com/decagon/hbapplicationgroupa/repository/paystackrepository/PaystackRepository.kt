package com.decagon.hbapplicationgroupa.repository.paystackrepository

import com.decagon.hbapplicationgroupa.model.paystack.InitializeTransaction
import com.decagon.hbapplicationgroupa.model.paystack.InitializeTransactionResponse
import com.decagon.hbapplicationgroupa.network.PaystackApiInterface
import retrofit2.Response
import javax.inject.Inject

class PaystackRepository @Inject constructor (private val paystackApiInterface: PaystackApiInterface)
    : PaystackRepositoryInterface{
    override suspend fun initializeTransactionResponse(payAuthToken: String, paymentInfo: InitializeTransaction): Response<InitializeTransactionResponse> {
        return paystackApiInterface.getInitTransactionResponse(payAuthToken, paymentInfo)
    }

}