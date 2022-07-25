package com.decagon.hbapplicationgroupa.adapter.exploreHomeAfterSearchAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.decagon.hbapplicationgroupa.R
import com.decagon.hbapplicationgroupa.databinding.ExploreHomeAfterSearchRecyclerviewItem2Binding
import com.decagon.hbapplicationgroupa.model.adaptermodels.TopHotel

class ExploreHomeAfterSearchRecyclerViewAdapter2(
    private var listOfTopDealHotels : List<TopHotel>,
    private val exploreHomeAfterSearchClickListener2: ExploreHomeAfterSearchClickListener2
): RecyclerView.Adapter<ExploreHomeAfterSearchRecyclerViewAdapter2.MyViewHolder>() {
    val binding : ExploreHomeAfterSearchRecyclerviewItem2Binding? = null

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hotelName2 = binding?.exploreHomeAfterSearchFragmentRecyclerViewTextviewName2
        val hotelPrice2 = binding?.exploreHomeAfterSearchFragmentRecyclerViewCancelledPrice2
        val hotelImage2 = binding?.exploreHomeAfterSearchFragmentRecyclerViewImageview2
        val cancelledPrice = binding?.exploreHomeAfterSearchFragmentRecyclerViewCancelledPrice2
    }

    interface ExploreHomeAfterSearchClickListener2 {
        fun exploreHomeAfterSearchClicked(position: Int)
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.explore_home_after_search_recyclerview_item_2, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: MyViewHolder,
        position: Int
    ) {
        holder.hotelImage2?.setImageResource(listOfTopDealHotels[position].image)
        holder.hotelName2?.text = listOfTopDealHotels[position].name
        holder.hotelPrice2?.text = listOfTopDealHotels[position].price.toString()
        holder.cancelledPrice?.text = listOfTopDealHotels[position].cancelledPrice.toString()

        holder.itemView.setOnClickListener {
            exploreHomeAfterSearchClickListener2.exploreHomeAfterSearchClicked(position)
        }
    }

    override fun getItemCount(): Int {
        return listOfTopDealHotels.size
    }


}