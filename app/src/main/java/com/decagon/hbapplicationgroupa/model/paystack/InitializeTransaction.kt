package com.decagon.hbapplicationgroupa.model.paystack

data class InitializeTransaction(
    val email: String,
    val amount: String,
    val reference: String
)
