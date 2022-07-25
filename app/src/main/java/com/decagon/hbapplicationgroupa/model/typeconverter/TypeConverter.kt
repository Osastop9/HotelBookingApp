package com.decagon.hbapplicationgroupa.model.typeconverter

import androidx.room.TypeConverter
import com.decagon.hbapplicationgroupa.model.hotelmodule.gethotelbyid.GetHotelByIdResponseItemReviews
import com.decagon.hbapplicationgroupa.model.hotelmodule.gethotelbyid.GetHotelByIdResponseItemRoomTypes
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TypeConverter {
    //Type converter for GetHotelByIdResponseItemRoomTypes custom data type
    @TypeConverter
    fun getHotelByIdResponseItemRoomTypesFromString(
        value: String
    ): ArrayList<GetHotelByIdResponseItemRoomTypes>{
        val listType = object: TypeToken<ArrayList<GetHotelByIdResponseItemRoomTypes>>(){}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun getHotelByIdResponseItemRoomTypesFromObjectType(
        value: ArrayList<GetHotelByIdResponseItemRoomTypes>
    ): String {
        return Gson().toJson(value)
    }

    //Type converter for GetHotelByIdResponseItemReviews custom data type
    @TypeConverter
    fun getHotelByIdResponseItemReviewsFromString(
        value: String
    ): ArrayList<GetHotelByIdResponseItemReviews>{
        val listType = object: TypeToken<ArrayList<GetHotelByIdResponseItemReviews>>(){}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun getHotelByIdResponseItemReviewsFromObjectType(
        value: ArrayList<GetHotelByIdResponseItemReviews>
    ): String {
        return Gson().toJson(value)
    }

    //Type converter for ArrayList<String> for gallery
    @TypeConverter
    fun galleryFromString(value: String): ArrayList<String>{
        val listType = object: TypeToken<ArrayList<String>>(){}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun galleryFromArrayList(value: ArrayList<String>): String{
        return Gson().toJson(value)
    }
}