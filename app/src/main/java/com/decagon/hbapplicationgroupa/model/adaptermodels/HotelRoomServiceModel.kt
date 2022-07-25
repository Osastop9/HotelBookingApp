package com.decagon.hbapplicationgroupa.model.adaptermodels

import com.decagon.hbapplicationgroupa.R

/**
 * Dataclass for HotelRoomService RecyclerView for the HotelDescription Fragment
 */
data class HotelRoomServiceModel(
    val roomImage: Int,
    val roomTitle: String,
    val roomPrice: String
){
    companion object{
        //Fake data list populated for UI sake
        val roomDataList = arrayListOf(
            HotelRoomServiceModel(R.drawable.ic_launcher_background, "Deluxe Room", "$6999"),
            HotelRoomServiceModel(R.drawable.ic_launcher_background, "Single Deluxe Room", "$5499"),
            HotelRoomServiceModel(R.drawable.ic_launcher_background, "Studio King", "$2599")
        )
    }
}