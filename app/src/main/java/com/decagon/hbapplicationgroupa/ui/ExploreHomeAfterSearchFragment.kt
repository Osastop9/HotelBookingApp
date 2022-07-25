package com.decagon.hbapplicationgroupa.ui

import android.os.Bundle
import android.view.*
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.decagon.hbapplicationgroupa.model.adaptermodels.Hotel
import com.decagon.hbapplicationgroupa.R
import com.decagon.hbapplicationgroupa.adapter.exploreHomeAfterSearchAdapter.ExploreHomeAfterSearchRecyclerViewAdapter1
import com.decagon.hbapplicationgroupa.adapter.exploreHomeAfterSearchAdapter.ExploreHomeAfterSearchRecyclerViewAdapter2
import com.decagon.hbapplicationgroupa.databinding.FragmentExploreHomeAfterSearchBinding
import com.decagon.hbapplicationgroupa.model.adaptermodels.TopHotel

class ExploreHomeAfterSearchFragment : Fragment(),
    ExploreHomeAfterSearchRecyclerViewAdapter1.ExploreHomeAfterSearchItemViewClickListener1,
    ExploreHomeAfterSearchRecyclerViewAdapter2.ExploreHomeAfterSearchClickListener2{

    private lateinit var adapter1: ExploreHomeAfterSearchRecyclerViewAdapter1
    private lateinit var adapter2: ExploreHomeAfterSearchRecyclerViewAdapter2
    private lateinit var recyclerView1: RecyclerView
    private lateinit var recyclerView2: RecyclerView

    //Set up view binding here
    private var _binding: FragmentExploreHomeAfterSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentExploreHomeAfterSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //navigating to top Hotel fragment
        binding.exploreHomeFragmentAftersearchTopHotelTopHotelViewAllTv.setOnClickListener {
            findNavController().navigate(R.id.action_exploreHomeAfterSearchFragment_to_topHotelsFragment)
        }

        //navigating to top Hotel fragment [no top Deal Fragment yet] - could be changed later
        binding.exploreHomeFragmentAftersearchTopDealViewAllTv.setOnClickListener {
            findNavController().navigate(R.id.action_exploreHomeAfterSearchFragment_to_topHotelsFragment)
        }

        onBackPressed()
        //creating dummy Hotel data
        val atlantisParadise = Hotel(
            1, "Atlantis Paradise", 6500,
            "9 Star Hotel", "99%", R.drawable.hotel_atlantis_paradise_bahamas
        )
        val burbArab = Hotel(
            2, "Burb Arab", 8500,
            "7 Star Hotel", "100%", R.drawable.hotel_burg_arab_dubai
        )
        val emiratePalace = Hotel(
            3, "Emirate Palace", 8900,
            "5 Star Hotel", "100%", R.drawable.hotel_emirates_palace_abu_dhabi
        )
        val meridianPalace = Hotel(
            4, "Meridian Palace", 5500,
            "9 Star Hotel", "98%", R.drawable.hotel_merdan_palace_turkey
        )
        val thePalms = Hotel(
            5, "The Palms", 6500,
            "6 Star Hotel", "99%", R.drawable.hotel_the_palms_las_vegas
        )
        val thePlaza = Hotel(
            6, "The Plaza", 7800,
            "9 Star Hotel", "100%", R.drawable.hotel_the_plaza_newyork
        )
        val westinExcelsior = Hotel(
            7, "Westin Excelsior", 9500,
            "12 Star Hotel", "100%", R.drawable.hotel_westin_excelsior_rome
        )

        val listOfHotels = listOf(
            atlantisParadise, burbArab, emiratePalace,
            meridianPalace, thePalms, thePlaza, westinExcelsior
        )

        //instantiate recyclerview to populate it
        adapter1 = ExploreHomeAfterSearchRecyclerViewAdapter1(listOfHotels, this)
        recyclerView1 = view.findViewById(R.id.exploreHomeAfterSearchFragmentrecyclerView)

        //populate data into recyclerview
        recyclerView1.adapter = adapter1
        recyclerView1.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView1.setHasFixedSize(false)


        //dummy data to populate 2nd recyclerView
        //creating dummy Hotel data
        val hotel1 = TopHotel(
            1, "Atlantis Paradise", 6500, 7500,
            "9 Star Hotel", "99%", R.drawable.hotel_atlantis_paradise_bahamas,
        )
        val hotel2 = TopHotel(
            2, "Burb Arab", 8500, 9500,
            "7 Star Hotel", "100%", R.drawable.hotel_burg_arab_dubai,
        )
        val hotel3 = TopHotel(
            3, "Emirate Palace", 8900, 9800,
            "5 Star Hotel", "100%", R.drawable.hotel_emirates_palace_abu_dhabi,
        )

        val listOfTopHotels = listOf(
            hotel1, hotel2, hotel3
        )

        //instantiate recyclerview to populate it
        adapter2 = ExploreHomeAfterSearchRecyclerViewAdapter2(listOfTopHotels, this)
        recyclerView2 = view.findViewById(R.id.exploreHomeAfterSearchFragmentRecyclerView2)

        //populate data into recyclerview
        recyclerView2.adapter = adapter2
        recyclerView2.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recyclerView2.setHasFixedSize(false)
    }

    override fun searchItemViewClicked(position: Int) {
        findNavController().navigate(R.id.action_exploreHomeAfterSearchFragment_to_hotelDescription2Fragment)
    }

    override fun exploreHomeAfterSearchClicked(position: Int) {
        findNavController().navigate(R.id.action_exploreHomeAfterSearchFragment_to_hotelDescription2Fragment)
    }

    //Method to handle back press
    private fun onBackPressed(){
        //Overriding onBack press to navigate to home Fragment onBack Pressed
        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }
}