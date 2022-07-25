package com.decagon.hbapplicationgroupa.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.decagon.hbapplicationgroupa.adapter.roomnumber_bottmshit_adapter.RoomIdByRoomTypeAdapterInterface
import com.decagon.hbapplicationgroupa.adapter.roomnumber_bottmshit_adapter.RoomNumberBottomSheetAdapter
import com.decagon.hbapplicationgroupa.databinding.FragmentNumberOfRoomsBottomSheetDialogBinding
import com.decagon.hbapplicationgroupa.model.hotelmodule.gethotelroombyid.GetHotelRoomByIdResponseItem
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NumberOfRoomsBottomSheetDialogFragment(
    private val roomNumbers: ArrayList<GetHotelRoomByIdResponseItem>,
    private val roomTypeAdapterInterface: RoomIdByRoomTypeAdapterInterface
) : BottomSheetDialogFragment(), RoomIdByRoomTypeAdapterInterface {
    private var _binding: FragmentNumberOfRoomsBottomSheetDialogBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: RoomNumberBottomSheetAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentNumberOfRoomsBottomSheetDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = RoomNumberBottomSheetAdapter(this, requireContext())
        binding.roomNumberBottomSheetRecyclerView.adapter = adapter
        binding.roomNumberBottomSheetRecyclerView.setHasFixedSize(true)

        //Receive rooms from booking details fragment and add them to the adapter for selection/display
       adapter.addRoomIdByRoomType(roomNumbers)

        //Click event to dismiss the bottom sheet
        binding.cancelButton.setOnClickListener {
            dismiss()
        }
    }

    override fun getSelectedRoomIdByRoomTypes(position: Int, data: String, toBookRoomId: String) {
        binding.doneButton.setOnClickListener {
            roomTypeAdapterInterface.getSelectedRoomIdByRoomTypes(position, data, toBookRoomId)
            dismiss()
        }
    }
}