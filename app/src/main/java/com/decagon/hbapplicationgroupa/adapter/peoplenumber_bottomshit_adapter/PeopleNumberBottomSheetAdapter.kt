package com.decagon.hbapplicationgroupa.adapter.peoplenumber_bottomshit_adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.decagon.hbapplicationgroupa.model.adaptermodels.PeopleNumberBottomSheetData
import com.decagon.hbapplicationgroupa.databinding.PeopleNumberBottomSheetViewHolderBinding

class PeopleNumberBottomSheetAdapter : RecyclerView.Adapter<PeopleNumberBottomSheetAdapter.PeopleNumberBottomSheetViewHolder>() {
    inner class PeopleNumberBottomSheetViewHolder(
        var binding: PeopleNumberBottomSheetViewHolderBinding
        ): RecyclerView.ViewHolder(binding.root)

    private val diffCallback = object: DiffUtil.ItemCallback<PeopleNumberBottomSheetData>(){
        override fun areItemsTheSame(oldItem: PeopleNumberBottomSheetData, newItem: PeopleNumberBottomSheetData): Boolean {
            return oldItem.peopleClass == newItem.peopleClass
        }

        override fun areContentsTheSame(oldItem: PeopleNumberBottomSheetData, newItem: PeopleNumberBottomSheetData): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeopleNumberBottomSheetViewHolder {
        val binding = PeopleNumberBottomSheetViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PeopleNumberBottomSheetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PeopleNumberBottomSheetViewHolder, position: Int) {
//        with(holder){
//            with(differ.currentList[position]){
//                binding.peopleClass.text = peopleClass
//                binding.ageRange.text = ageRange
//                binding.numberCount.text = numberCount
//
//                var count = 0
//
//                binding.addButton.setOnClickListener {
//                    count++
//                    binding.numberCount.text = count.toString()
//                }
//
//                binding.minusButton.setOnClickListener {
//                    if (count > 0){
//                        count--
//                        binding.numberCount.text = count.toString()
//                    }
//                }
//            }
//        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}