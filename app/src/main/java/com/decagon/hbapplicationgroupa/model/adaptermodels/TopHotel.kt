package com.decagon.hbapplicationgroupa.model.adaptermodels

data class TopHotel(
    val id:Int,
    val name:String,
    val price:Int,
    val cancelledPrice: Int,
    val classOfHotel: String,
    val rating: String,
    val image : Int
)
