package com.decagon.hbapplicationgroupa.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.decagon.hbapplicationgroupa.databinding.FragmentNumberOfPeopleBottomSheetDialogBinding
import com.decagon.hbapplicationgroupa.utils.PeopleBottomSheetOnClickInterface
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NumberOfPeopleBottomSheetDialogFragment(
    private val onClickInterface: PeopleBottomSheetOnClickInterface
    ) : BottomSheetDialogFragment() {
    private var _binding: FragmentNumberOfPeopleBottomSheetDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentNumberOfPeopleBottomSheetDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Dismiss the bottom sheet when the cancel button is clicked
        binding.cancelButton.setOnClickListener {
            dismiss()
        }

        //Implement counter functionality on the number of people
        var adultCount = 0
        binding.adultAddButton.setOnClickListener {
            adultCount++
            binding.adultNumberCount.text = adultCount.toString()
        }

        binding.adultMinusButton.setOnClickListener {
            if (adultCount > 0){
                adultCount--
                binding.adultNumberCount.text = adultCount.toString()
            }
        }

        var teensCount = 0
        binding.teenAddButton.setOnClickListener {
            teensCount++
            binding.teenNumberCount.text = teensCount.toString()
        }

        binding.teenMinusButton.setOnClickListener {
            if (teensCount > 0){
                teensCount--
                binding.teenNumberCount.text = teensCount.toString()
            }
        }

        var childrenCount = 0
        binding.childrenAddButton.setOnClickListener {
            childrenCount++
            binding.childrenNumberCount.text = childrenCount.toString()
        }

        binding.childrenMinusButton.setOnClickListener {
            if (childrenCount > 0){
                childrenCount--
                binding.childrenNumberCount.text = childrenCount.toString()
            }
        }

        var infantsCount = 0
        binding.infantsAddButton.setOnClickListener {
            infantsCount++
            binding.infantsNumberCount.text = infantsCount.toString()
        }

        binding.infantsMinusButton.setOnClickListener {
            if (infantsCount > 0){
                infantsCount--
                binding.infantsNumberCount.text = infantsCount.toString()
            }
        }

        //Save users selections in a list and pass them to the edit text in the booking details fragment
        binding.doneButton.setOnClickListener {
            val peopleData = mutableListOf<String>()
            var peopleCount = 0
            var adultsData = ""
            var teensData = ""
            var childrenData = ""
            var infantsData = ""

            if (adultCount == 1){
                adultsData = "$adultCount adult"
            }else if (adultCount > 1){
                adultsData = "$adultCount adults"
            }

            if (teensCount == 1){
                teensData = "$teensCount teen"
            }else if(teensCount > 1){
                teensData = "$teensCount teens"
            }

            if (childrenCount == 1){
                childrenData = "$childrenCount child"
            }else if (childrenCount > 1){
                childrenData = "$childrenCount children"
            }

            if (infantsCount == 1){
                infantsData = "$infantsCount infant"
            }else if(infantsCount > 1){
                infantsData = "$infantsCount infants"
            }

            if (adultCount > 0){
                peopleData.add(adultsData)
                peopleCount+=adultCount
            }

            if (teensCount > 0){
                peopleData.add(teensData)
                peopleCount+=teensCount
            }

            if (childrenCount > 0){
                peopleData.add(childrenData)
                peopleCount+=childrenCount
            }

            if (infantsCount > 0){
                peopleData.add(infantsData)
                peopleCount+=infantsCount
            }

            val peopleDataToString = peopleData.joinToString(", ")

            //Send number of people info selected by a user to the booking details fragment
            onClickInterface.passDataFromPeopleBottomSheetToBookingDetailsScreen(peopleDataToString)
            dismiss()
        }
    }
}