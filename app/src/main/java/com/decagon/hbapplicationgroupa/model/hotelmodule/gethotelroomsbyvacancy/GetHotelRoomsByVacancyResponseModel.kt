package com.decagon.hbapplicationgroupa.model.hotelmodule.gethotelroomsbyvacancy

data class GetHotelRoomsByVacancyResponseModel(
    val statusCode: String,
    val success: Boolean,
    val Data: ArrayList<GetHotelRoomsByVacancyResponseItem>,
    val Message: String,
    val errors: String?
)
