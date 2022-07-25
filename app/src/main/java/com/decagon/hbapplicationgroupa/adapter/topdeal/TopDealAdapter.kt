package com.decagon.hbapplicationgroupa.adapter.topdeal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.decagon.hbapplicationgroupa.R
import com.decagon.hbapplicationgroupa.databinding.TopDealRecyclerviewViewItemBinding
import com.decagon.hbapplicationgroupa.model.hotelmodule.gettopdeals.GetTopDealsResponseItem

class TopDealAdapter(
    private val topDealItemClickListener: TopDealItemClickListener,
    private val topDealPreviewBtnClickListener: TopDealPreviewBtnClickListener,
    private val topDealSaveIconClickListener: TopDealSaveIconClickListener
) : RecyclerView.Adapter<TopDealAdapter.TopDealViewHolder>() {

    var topDealList: List<GetTopDealsResponseItem> = listOf()
    val binding: TopDealRecyclerviewViewItemBinding? = null
    inner class TopDealViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val nameOfDeal = view.findViewById<TextView>(R.id.topDeal_recyclerview_hotel_name)
        val priceOfDeal = view.findViewById<TextView>(R.id.topDeal_recyclerview_hotel_price)
        val classOfDeal = view.findViewById<TextView>(R.id.topDeal_recyclerview_hotel_status)
        val ratingOfDeal = view.findViewById<TextView>(R.id.topDeal_recyclerview_hotel_rating)
        val imageOfDeal = view.findViewById<ImageView>(R.id.topDeal_recyclerview_imageview)
        val hotelBookBtn: AppCompatButton = itemView.findViewById(R.id.topDealBookBtn)
        val topDealView: CardView = itemView.findViewById(R.id.topDealView)
        val saveIcon =  view.findViewById<ImageView>(R.id.topDeal_recyclerview_imageview_save)
        val saveText = view.findViewById<TextView>(R.id.topDeal_recyclerview_textview)
    }

    interface TopDealItemClickListener {
        fun topHotelsItemClicked(position: Int)
    }

    interface TopDealPreviewBtnClickListener {
        fun topHotelsPreviewBtnClicked(position: Int)
    }

    interface TopDealSaveIconClickListener{
        fun topDealSaveIconClicked(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopDealViewHolder {
        return TopDealViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.top_deal_recyclerview_view_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: TopDealViewHolder, position: Int) {
        holder.nameOfDeal?.text = topDealList[position].name
        holder.priceOfDeal?.text = "$${topDealList[position].price}"
//        holder.classOfDeal?.text = topDealList[position].description
//        holder.ratingOfDeal?.text = "${topDealList[position].discount}% OFF"
        holder.imageOfDeal?.let {
            Glide.with(it.context)
                .load(topDealList[position].thumbnail)
//                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.imageOfDeal)
        };

        holder.topDealView.setOnClickListener {
            topDealItemClickListener.topHotelsItemClicked(position)
        }

        holder.hotelBookBtn.setOnClickListener {
            topDealPreviewBtnClickListener.topHotelsPreviewBtnClicked(position)
        }

        holder.saveIcon.setOnClickListener{
            topDealSaveIconClickListener.topDealSaveIconClicked(position)
            holder.saveText.text = "Successfully Saved"
        }

    }

    override fun getItemCount(): Int {
        return topDealList.size
    }
//    fun setHotel(hotel:List<GetTopDealsResponseItem>){
//        this.topDealList = hotel
//        notifyDataSetChanged()
//    }
}