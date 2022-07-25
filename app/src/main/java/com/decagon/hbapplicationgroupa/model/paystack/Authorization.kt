package com.decagon.hbapplicationgroupa.model.paystack

data class Authorization(
    val authorization_code: String,
    val bank: String,
    val bin: String,
    val brand: String,
    val card_type: String,
    val channel: String,
    val country_code: String,
    val exp_month: String,
    val exp_year: String,
    val last4: String,
    val reusable: Boolean,
    val signature: String
)