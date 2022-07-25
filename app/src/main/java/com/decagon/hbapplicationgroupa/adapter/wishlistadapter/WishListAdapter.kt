package com.decagon.hbapplicationgroupa.adapter.wishlistadapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.decagon.hbapplicationgroupa.R
import com.decagon.hbapplicationgroupa.databinding.WishListItemsBinding
import com.decagon.hbapplicationgroupa.model.customermodule.getcustomerwishlistbypagenumber.PageItem


class WishListAdapter(
    var context: Context,
    private val wishListItemClickListener: WishListItemClickListener,
    private val wishListPreviewButtonClickListener: WishListPreviewButtonClickListener,
    private val wishListRemoveButtonClickListener: WishListRemoveButtonClickListener
    ): RecyclerView.Adapter<WishListAdapter.WishListViewHolder>() {

     var listOfWishList = arrayListOf<PageItem>()

    inner class WishListViewHolder(binding: WishListItemsBinding) : RecyclerView.ViewHolder(binding.root) {
        var hotelPrice: TextView = binding.tvHotelprice
        var hotelName: TextView = binding.tvNameOfHotel
        var hotelRating: TextView = binding.tvHotelRating
        val removeText: TextView = binding.tvRemove
        val removeIcon: ImageView = binding.removeIcon
        val previewBtn: Button = binding.bookingBtn
        val savedImage: ImageView = binding.hotelImageView

    }

    fun removeHotelFromWishList(){

    }

    interface WishListItemClickListener {
        fun wishListClicked(position: Int)
    }

    interface WishListPreviewButtonClickListener {
        fun wishListPreviewBtnClicked(position: Int)
    }

    interface WishListRemoveButtonClickListener {
        fun wishlistRemoveBtnClicked(position: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WishListViewHolder {
        val binding = WishListItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return WishListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WishListViewHolder, position: Int) {
        holder.hotelName.text = listOfWishList[position].hotelName
        holder.savedImage.let {
            Glide.with(it.context)
                .load(listOfWishList[position].imageUrl)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.savedImage)
        }


        holder.apply {

            // set the onclick listener to the bookmark icon
            removeIcon.setOnClickListener {
                wishListRemoveButtonClickListener.wishlistRemoveBtnClicked(position)
                while (position < listOfWishList.size){
                    listOfWishList.remove(listOfWishList[position])
                    break
                }
                notifyDataSetChanged()
            }

            // set the onclick listener to the booking button
            previewBtn.setOnClickListener {
                wishListPreviewButtonClickListener.wishListPreviewBtnClicked(position)
            }

            //click listener for each saved Item
            holder.savedImage.setOnClickListener {
                wishListItemClickListener.wishListClicked(position)
            }
        }
    }
    override fun getItemCount() = listOfWishList.size

    //setting data to listOfWishList
    fun setDataToAdapter(list: ArrayList<PageItem>){
//        listOfWishList.clear()
        listOfWishList = list
        notifyDataSetChanged()
    }

}