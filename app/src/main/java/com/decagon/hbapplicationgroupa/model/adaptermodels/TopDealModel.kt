package com.decagon.hbapplicationgroupa.model.adaptermodels

data class TopDealModel(
    val hotelId: String,
    val name: String,
    val description: String,
    val price: Double,
    val discount: Double,
    val thumbnail: String,
    val hotelName: String
) {
//    //Dummy data
//    companion object{
//        val topDealList = listOf(
//            TopDealModel(
//                1, "Atlantis Paradise", 6500,
//                "9 Star Hotel", "99%", R.drawable.hotel_atlantis_paradise_bahamas
//            ),
//            TopDealModel(
//                2, "Burb Arab", 8500,
//                "7 Star Hotel", "100%", R.drawable.hotel_burg_arab_dubai
//            )
//        )
//    }
}