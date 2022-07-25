package com.decagon.hbapplicationgroupa.adapter.stackedreviewadapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.decagon.hbapplicationgroupa.R
import com.decagon.hbapplicationgroupa.model.hotelmodule.gethotelbyid.GetHotelByIdResponseItemReviews
import com.mikhaellopez.circularimageview.CircularImageView

/**
 * This is the adapter for the id: hotelDescOverlapRv RecyclerView in HotelDescription Fragment
 */
class StackedReviewAdapter : RecyclerView.Adapter<StackedReviewAdapter.StackedReviewViewHolder>(){
    var stackedImageList : ArrayList<GetHotelByIdResponseItemReviews> = arrayListOf()

    class StackedReviewViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val img : CircularImageView = view.findViewById(R.id.stackedReviewIv)

        //Bind image fetched from the server to views
        fun bindData(image: GetHotelByIdResponseItemReviews){
            Glide.with(itemView)
                .load(image.customerImage)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(img)
        }
    }

    //Add images to the adapter's list
    @SuppressLint("NotifyDataSetChanged")
    fun addReviewerImages(images: ArrayList<GetHotelByIdResponseItemReviews>){
        stackedImageList.clear()
        stackedImageList.addAll(images)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StackedReviewViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.hotel_desc_stacked_review_recyclerview, parent, false)
        return StackedReviewViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: StackedReviewViewHolder, position: Int) {
        holder.bindData(stackedImageList[position])
    }

    override fun getItemCount(): Int {
        return stackedImageList.size
    }
}