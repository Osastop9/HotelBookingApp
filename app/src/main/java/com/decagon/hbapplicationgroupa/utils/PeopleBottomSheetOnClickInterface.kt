package com.decagon.hbapplicationgroupa.utils

//This interface sends number of people info selected by a user to the booking details fragment
interface PeopleBottomSheetOnClickInterface {
    fun passDataFromPeopleBottomSheetToBookingDetailsScreen(data: String)
}