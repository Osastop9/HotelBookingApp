package com.decagon.hbapplicationgroupa.adapter.allHotelsAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.decagon.hbapplicationgroupa.R
import com.decagon.hbapplicationgroupa.model.hotelmodule.allhotels.PageItem
import com.decagon.hbapplicationgroupa.repository.customermodulerepository.CustomerRepository
import kotlin.math.ceil


class AllHotelsAdapter(
        val allHotelsItemClickListener: AllHotelsItemClickListener,
        val allHotelsBookBtnClickListener: AllHotelsBookBtnClickListener,
        val allHotelSaveIconClickListener: AllHotelSaveIconClickListener
): RecyclerView.Adapter<AllHotelsAdapter.AllHotelsViewHolder>() {

    var listOfAllHotels: MutableList<PageItem> = mutableListOf()
    private lateinit var customerRepository: CustomerRepository

    inner class AllHotelsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val nameOfAllHotel: TextView = itemView.findViewById(R.id.allHotels_recyclerview_hotel_name)
        val priceOfAllHotel: TextView = itemView.findViewById(R.id.allHotels_recyclerview_hotel_price)
        val ratingOfAllHotel: TextView = itemView.findViewById(R.id.allHotels_recyclerview_hotel_rating)
        val imageOfAllHotel: ImageView = itemView.findViewById(R.id.allHotels_recyclerview_imageview)
        val saveTextView: TextView = itemView.findViewById(R.id.allHotels_recyclerview_textview)
        val saveIcon: ImageView = itemView.findViewById(R.id.allHotels_recyclerview_imageview_save)
        val allHotelBookBtn: AppCompatButton = itemView.findViewById(R.id.allHotelsBookBtn)
        val allHotelView: CardView = itemView.findViewById(R.id.allHotelsView)
        val bind: TextView = itemView.findViewById(R.id.tv_notification)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllHotelsViewHolder {
        return AllHotelsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_all_hotels_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: AllHotelsViewHolder, position: Int) {
        holder.nameOfAllHotel.text = listOfAllHotels[position].name
        listOfAllHotels[position].roomTypes?.forEach {
            holder.priceOfAllHotel.text = it.price.toString()
        }
        holder.ratingOfAllHotel.text = listOfAllHotels[position].rating?.let { ceil(it).toString() }
        holder.imageOfAllHotel.let {
            Glide.with(it.context)
                .load(listOfAllHotels[position].featuredImage)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.imageOfAllHotel)
        }
        holder.allHotelView.setOnClickListener{
            allHotelsItemClickListener.allHotelsItemClicked(position)
        }

        holder.allHotelBookBtn.setOnClickListener{
            allHotelsBookBtnClickListener.allHotelsPreviewBtnClicked(position)
        }

        holder.saveIcon.setOnClickListener{
            allHotelSaveIconClickListener.allHotelSaveIconClickListener(position)
            holder.saveTextView.text = "Successfully saved"
        }

    }

    override fun getItemCount(): Int {
        return listOfAllHotels.size
    }

    interface AllHotelsItemClickListener {
        fun allHotelsItemClicked(position: Int)
    }

    interface AllHotelsBookBtnClickListener {
        fun allHotelsPreviewBtnClicked(position: Int)
    }

    interface AllHotelSaveIconClickListener{
        fun allHotelSaveIconClickListener(position: Int)
    }


    fun setList(list: MutableList<PageItem>){
        listOfAllHotels.clear()
        listOfAllHotels = list
        notifyDataSetChanged()
    }
}