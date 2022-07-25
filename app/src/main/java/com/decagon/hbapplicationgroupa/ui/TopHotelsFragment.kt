package com.decagon.hbapplicationgroupa.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.decagon.hbapplicationgroupa.adapter.allHotelsAdapter.AllHotelsAdapter
import com.decagon.hbapplicationgroupa.adapter.topHotel.TopHotelAdapter
import com.decagon.hbapplicationgroupa.database.AuthPreference
import com.decagon.hbapplicationgroupa.databinding.FragmentTopHotelsBinding
import com.decagon.hbapplicationgroupa.model.hotelmodule.gettophotels.GetTopHotelsResponseItem
import com.decagon.hbapplicationgroupa.viewModel.HotelViewModel
import com.decagon.hbapplicationgroupa.viewmodel.CustomerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopHotelsFragment : Fragment(),
    TopHotelAdapter.TopHotelsItemClickListener,
    TopHotelAdapter.TopHotelsBookBtnClickListener,
    TopHotelAdapter.TopHotelSaveIconClickListener,
    TopHotelAdapter.TopHotelSaveTextClickListener{

    //Set up view binding here
    private var _binding: FragmentTopHotelsBinding? = null
    private val binding get() = _binding!!

    private lateinit var topHotelAdapter: TopHotelAdapter
    private lateinit var allHotelAdapter: AllHotelsAdapter
    val hotelViewModel : HotelViewModel by viewModels()
    val customerViewModel : CustomerViewModel by viewModels()
    lateinit var recyclerView: RecyclerView
    lateinit var hotelId: String
    lateinit var hotelList: List<GetTopHotelsResponseItem>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentTopHotelsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hotelList = listOf()

        //connecting the view with the data response
        showProgressBar()
        setupRecyclerView()
        hotelViewModel.fetchTopScreenHotels()
        hotelViewModel.topHotelsLiveData.observe(viewLifecycleOwner,{
            Log.d("Frag -> TopHotel", it.toString())
            topHotelAdapter.setListOfTopHotels(it)
            hotelList = it
            hideProgressBar()
        })

        // setting back button
        val backButton = binding.topHotelBackBtn
        backButton.setOnClickListener{
            findNavController().popBackStack()
        }

        //setting view button
        onBackPressed()
    }

    override fun topHotelsItemClicked(position: Int) {
        val item = hotelList[position]
        hotelId = item.id
        val action = TopHotelsFragmentDirections.actionTopHotelsFragmentToHotelDescription2Fragment(hotelId)
        findNavController().navigate(action)
//        findNavController().navigate(R.id.action_topHotelsFragment_to_hotelDescription2Fragment)
    }

    override fun topHotelsPreviewBtnClicked(position: Int) {
        val item = hotelList[position]
        hotelId = item.id
        val action = TopHotelsFragmentDirections.actionTopHotelsFragmentToHotelDescription2Fragment(hotelId)
        findNavController().navigate(action)
//        findNavController().navigate(R.id.action_topHotelsFragment_to_bookingDetailsFragment)
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

    @SuppressLint("ResourceAsColor")
    override fun topHotelSaveIconClickListener(position: Int) {
        AuthPreference.initPreference(requireActivity())
        val authToken = "Bearer ${AuthPreference.getToken(AuthPreference.TOKEN_KEY)}"
        val hotelWish = hotelList[position]
        customerViewModel.addWishList(authToken, hotelWish.id)
        customerViewModel.getWishList(authToken, 10, 1)
    }

    override fun topHotelSaveTextClickListener(position: Int) {
        AuthPreference.initPreference(requireActivity())
        val authToken = "Bearer ${AuthPreference.getToken(AuthPreference.TOKEN_KEY)}"
        val hotelWish = hotelList[position]
        customerViewModel.addWishList(authToken, hotelWish.id)
        customerViewModel.getWishList(authToken, 10, 1)
//        Toast.makeText(requireContext(),
//            "${hotelList[position].name} is successfully deleted from WishList",
//            Toast.LENGTH_SHORT).show()
    }

    //show progress bar
    private fun showProgressBar(){
        binding.topHotelProgressBar.visibility = View.VISIBLE
    }

    //hide progress bar
    private fun hideProgressBar() {
        binding.topHotelProgressBar.visibility = View.INVISIBLE
    }

    //set up recycler view
    private fun setupRecyclerView() {
        topHotelAdapter = TopHotelAdapter(
            requireContext(),
            this,
            this,
            this,
            this)
        binding.topHotelRecyclerview.apply {
            layoutManager = LinearLayoutManager(requireContext())
            this.adapter = topHotelAdapter
        }
    }
}