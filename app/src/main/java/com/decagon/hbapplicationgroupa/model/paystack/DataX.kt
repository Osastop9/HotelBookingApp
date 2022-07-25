package com.decagon.hbapplicationgroupa.model.paystack

data class DataX(
    val amount: Int,
    val authorization: Authorization,
    val channel: String,
    val currency: String,
    val customer: Customer,
    val domain: String,
    val fees: Int,
    val gateway_response: String,
    val ip_address: String,
    val log: Any,
    val message: String,
    val metadata: MetadataX,
    val plan: Any,
    val reference: String,
    val status: String,
    val transaction_date: String
)