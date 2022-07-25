package com.decagon.hbapplicationgroupa.adapter.topHotel

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.decagon.hbapplicationgroupa.R
import com.decagon.hbapplicationgroupa.databinding.TopHotelRecyclerviewViewItemBinding
import com.decagon.hbapplicationgroupa.model.hotelmodule.gettophotels.GetTopHotelsResponseItem
import kotlin.math.ceil


class TopHotelAdapter(
    val context : Context,
    private val topHotelsItemClickListener: TopHotelsItemClickListener,
    private val topHotelsSaveIconClickListener: TopHotelSaveIconClickListener,
    private val topHotelsBookBtnClickListener: TopHotelsBookBtnClickListener,
    private val topHotelsSaveTextClickListener: TopHotelSaveTextClickListener
    ): RecyclerView.Adapter<TopHotelAdapter.ViewHolder>(){

    private var listOfTopHotels = arrayListOf<GetTopHotelsResponseItem>()

    inner class ViewHolder(binding: TopHotelRecyclerviewViewItemBinding): RecyclerView.ViewHolder(binding.root) {
        val nameOfHotel = binding.topHotelRecyclerviewHotelName
        val priceOfHotel = binding.topHotelRecyclerviewHotelPrice
        val classOfHotel = binding.topHotelRecyclerviewHotelStatus
        val ratingOfHotel = binding.topHotelRecyclerviewHotelRating
        val imageOfHotel = binding.topHotelRecyclerviewImageview
        val saveIcon = binding.topHotelRecyclerviewImageviewSave
        val saveText = binding.topHotelRecyclerviewSaveTv
        val hotelBookBtn: AppCompatButton = itemView.findViewById(R.id.topHotelBookBtn)
        val topHotelView: CardView = itemView.findViewById(R.id.topHotelView)
    }

    interface TopHotelsItemClickListener {
        fun topHotelsItemClicked(position: Int)
    }

    interface TopHotelsBookBtnClickListener {
        fun topHotelsPreviewBtnClicked(position: Int)
    }

    interface TopHotelSaveIconClickListener{
        fun topHotelSaveIconClickListener(position: Int)
    }

    interface TopHotelSaveTextClickListener{
        fun topHotelSaveTextClickListener(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TopHotelRecyclerviewViewItemBinding.inflate(
            LayoutInflater.from(parent.context),parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameOfHotel.text = listOfTopHotels[position].name
        holder.priceOfHotel.text = listOfTopHotels[position].price
        holder.classOfHotel.text = listOfTopHotels[position].description
        holder.ratingOfHotel.text = ceil(listOfTopHotels[position].percentageRating).toString()
        holder.imageOfHotel.let {
            Glide.with(holder.itemView)
                .load(listOfTopHotels[position].thumbnail)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(it)
        }

        holder.topHotelView.setOnClickListener {
            topHotelsItemClickListener.topHotelsItemClicked(position)
        }

        holder.hotelBookBtn.setOnClickListener {
            topHotelsBookBtnClickListener.topHotelsPreviewBtnClicked(position)
        }

        holder.saveIcon.setOnClickListener {
            topHotelsSaveIconClickListener.topHotelSaveIconClickListener(position)
            holder.saveText.text = "Successfully Saved"
        }

    }

    override fun getItemCount(): Int {
       return listOfTopHotels.size
    }

    fun setListOfTopHotels(topHotels: ArrayList<GetTopHotelsResponseItem>) {
        listOfTopHotels = topHotels
        Log.d("newList", listOfTopHotels.toString())
        notifyDataSetChanged()
    }
}