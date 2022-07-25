package com.decagon.hbapplicationgroupa.adapter.pastbookings_adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.decagon.hbapplicationgroupa.R
import com.decagon.hbapplicationgroupa.model.customermodule.getCustomerBooking.PageItem
import com.google.android.material.button.MaterialButton

class PastBookingsAdapter(
    val pastBookingBookingClickListener: PastBookingBookClickListener
    ): RecyclerView.Adapter<PastBookingsAdapter.MyViewHolder>() {

    var bookingList:  List<PageItem> = listOf()

    class MyViewHolder(view: View):RecyclerView.ViewHolder(view){
        val bookBtn = view.findViewById<MaterialButton>(R.id.fragment_booking_book_btn)
        var checkInTime: TextView = view.findViewById(R.id.fragment_booking_time_tv)
        var bookedHotel: TextView = view.findViewById(R.id.fragment_booking_hotel_tv)
        var location: TextView = view.findViewById(R.id.fragment_booking_location_tv)
        var price: TextView = view.findViewById(R.id.fragment_booking_price)


        fun bind(booking: PageItem){
            checkInTime.text = booking.checkIn
            bookedHotel.text = booking.hotel
            price.text = booking.price.toString()
        }
    }

    interface PastBookingBookClickListener {
        fun pastBookBtnClicked(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.past_booking_items, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return bookingList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        holder.bind(getItem(position)!!)
        holder.bind(bookingList[position])
        holder.bookBtn.setOnClickListener {
            pastBookingBookingClickListener.pastBookBtnClicked(position)
        }
    }

//    class DiffUtilCallBack: DiffUtil.ItemCallback<PageItem>() {
//        override fun areItemsTheSame(
//            oldItem: PageItem,
//            newItem: PageItem
//        ): Boolean {
//
//            return oldItem.id == newItem.id
//        }
//
//        override fun areContentsTheSame(
//            oldItem: PageItem,
//            newItem: PageItem
//        ): Boolean {
//            return oldItem == newItem
//        }
//
//    }


}