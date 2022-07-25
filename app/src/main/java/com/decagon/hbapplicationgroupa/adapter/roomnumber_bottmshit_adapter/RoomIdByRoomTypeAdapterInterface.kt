package com.decagon.hbapplicationgroupa.adapter.roomnumber_bottmshit_adapter

//This interface sends data from rooms bottom sheet to booking details fragment
interface RoomIdByRoomTypeAdapterInterface {
        fun getSelectedRoomIdByRoomTypes(position: Int, data: String, toBookRoomId: String)
}