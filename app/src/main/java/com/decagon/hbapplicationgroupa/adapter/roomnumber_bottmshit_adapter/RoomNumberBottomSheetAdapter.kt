package com.decagon.hbapplicationgroupa.adapter.roomnumber_bottmshit_adapter

import android.content.Context
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.decagon.hbapplicationgroupa.databinding.RoomNumberBottomSheetViewHolderBinding
import com.decagon.hbapplicationgroupa.model.hotelmodule.gethotelroombyid.GetHotelRoomByIdResponseItem
import com.decagon.hbapplicationgroupa.utils.LoginValidations.enable


class RoomNumberBottomSheetAdapter(
    private val roomIdByRoomTypeAdapterInterface: RoomIdByRoomTypeAdapterInterface,
    private val context: Context
): RecyclerView.Adapter<RoomNumberBottomSheetAdapter.RoomNumberBottomSheetViewHolder>() {

    inner class RoomNumberBottomSheetViewHolder(
        var binding: RoomNumberBottomSheetViewHolderBinding
    ): RecyclerView.ViewHolder(binding.root)

    private var listOfRoomsByRoomId = arrayListOf<GetHotelRoomByIdResponseItem>()

   // var listOfRoomsByName = arrayListOf<GetHotelByIdResponseItemRoomTypes>()


//    fun addRoomNumber(roomNumber: ArrayList<GetHotelRoomByIdResponseItem>){
//        listOfRoomsByNumber.addAll(roomNumber)
//    }

//    fun addRoomType(roomName: ArrayList<GetHotelByIdResponseItemRoomTypes>){
//        listOfRoomsByName.addAll(roomName)
//    }

    fun addRoomIdByRoomType(roomNumber: ArrayList<GetHotelRoomByIdResponseItem>) {
        listOfRoomsByRoomId.addAll(roomNumber)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomNumberBottomSheetViewHolder {
        val binding = RoomNumberBottomSheetViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RoomNumberBottomSheetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoomNumberBottomSheetViewHolder, position: Int) {
        with(holder) {
            with(listOfRoomsByRoomId[position]) {
                binding.roomNumber.text = "Room $roomNo"
                binding.roomId.text = id
                if (isBooked) {
                   binding.roomNumber.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                    binding.roomTypeCheckbox.enable(false)
                    binding.roomNumber.setOnClickListener {
                        Toast.makeText(context, "Room $roomNo is not available", Toast.LENGTH_SHORT).show()
                    }
                }
            }
//            with(listOfRoomsByNumber[position]){
//                binding.roomNumber.text = roomNo.toString

//                with(listOfRoomsByName[position]) {
//                    binding.roomName.text = name
//                }
            //  binding.roomTypeNumberCount.text = numberOfRooms

//            val roomsByIdList = arrayListOf<String>()
//            val roomsByRoomNoList = arrayListOf<String>()

            var toBookRoomId = ""
            var toBookRoomNum = ""
//                binding.roomTypeCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
//                    if (isChecked){
//                        roomTypeList.add(binding.roomType.text.toString())
//                        Log.d("GKB", "$roomTypeList")
//                    }
//                }

            //Get selection from adapter and send it to rooms bottom sheet fragment
            binding.roomTypeCheckbox.setOnClickListener {
                if (binding.roomTypeCheckbox.isChecked) {
                    toBookRoomId = binding.roomId.text.toString()
                    toBookRoomNum = binding.roomNumber.text.toString()
//                    roomsByIdList.add(binding.roomId.text.toString())
//                    roomsByRoomNoList.add(binding.roomNumber.text.toString())
//                    Log.d("GKB", "$roomsByIdList")
                } else {
                    //  roomTypeList.remove(binding.roomType.text.toString())
                }

              //  val data = roomsByIdList.joinToString(", ")
//                val getroomId = roomsByIdList.joinToString { ", " }
//                val getroomNum = roomsByRoomNoList.joinToString(", ")
                roomIdByRoomTypeAdapterInterface.getSelectedRoomIdByRoomTypes(position, toBookRoomNum, toBookRoomId)
            }
        }
    }

    override fun getItemCount() = listOfRoomsByRoomId.size

}