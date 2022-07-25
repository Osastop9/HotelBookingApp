package com.decagon.hbapplicationgroupa.model.paystack

data class Customer(
    val customer_code: String,
    val email: String,
    val first_name: String,
    val id: Int,
    val last_name: String,
    val metadata: Metadata,
    val phone: String,
    val risk_action: String
)