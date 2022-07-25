package com.decagon.hbapplicationgroupa.adapter.hotelgalleryadapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.decagon.hbapplicationgroupa.R

/**
 * This is the Adapter for hotel_desc_viewPager
 */
class HotelGalleryAdapter : RecyclerView.Adapter<HotelGalleryAdapter.GalleryViewHolder>() {
    private var galleryList: ArrayList<String> = arrayListOf()

    class GalleryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val galleryImage: ImageView = view.findViewById(R.id.hotel_gallery_iv)

        //Bind image fetched from the server to views
        fun bindGalleryImage(image: String){
            Glide.with(itemView)
                .load(image)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(galleryImage)
        }
    }

    //Add images to the adapter's list
    @SuppressLint("NotifyDataSetChanged")
    fun addImageToGallery(image: ArrayList<String>){
        galleryList.clear()
        galleryList.addAll(image)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.hotel_gallery_viewpager, parent, false)
        return GalleryViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.bindGalleryImage(galleryList[position])
    }

    override fun getItemCount(): Int {
        return galleryList.size
    }
}