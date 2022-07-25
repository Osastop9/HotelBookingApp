package com.decagon.hbapplicationgroupa.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.decagon.hbapplicationgroupa.adapter.pastbookings_adapter.PastBookingsAdapter
import com.decagon.hbapplicationgroupa.R
import com.decagon.hbapplicationgroupa.database.AuthPreference
import com.decagon.hbapplicationgroupa.databinding.FragmentPastBookingsBinding
import com.decagon.hbapplicationgroupa.model.customermodule.getCustomerBooking.PageItem
import com.decagon.hbapplicationgroupa.viewmodel.CustomerViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PastBookingsFragment : Fragment(), PastBookingsAdapter.PastBookingBookClickListener {
    private var _binding: FragmentPastBookingsBinding? = null
    private val binding get() = _binding!!
    lateinit var adapter: PastBookingsAdapter
    lateinit var viewModel: CustomerViewModel
    lateinit var hotelId: String
    lateinit var hotelList: List<PageItem>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPastBookingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hotelList = listOf()
        viewModel = ViewModelProvider(this)[CustomerViewModel::class.java]
        binding.fragmentBookingBackIv.setOnClickListener {
            findNavController().navigate(R.id.action_pastBookingsFragment2_to_profileFragment)
        }

        //TODO: Set click event on recycler view item
        binding.fragmentBookingBackIv.setOnClickListener {
            findNavController().navigate(R.id.action_pastBookingsFragment2_to_profileFragment)
        }

        adapter = PastBookingsAdapter(this)
        binding.bookingRecyclerview.adapter = adapter
        binding.bookingRecyclerview.layoutManager = LinearLayoutManager(requireContext())

        AuthPreference.initPreference(requireActivity())
        val authToken = "Bearer ${AuthPreference.getToken(AuthPreference.TOKEN_KEY)}"
        getPastBookings(authToken)
        observeBookingHistoryFlow()
        displayNoBookingImage()
        onBackPressed()
    }

    private fun displayNoBookingImage(){
        if (binding.bookingRecyclerview.isVisible){
            binding.noBookingTxt.text = ""
        }else{
            binding.noBookingTxt.text = "You have made no bookings"
        }
    }

    override fun pastBookBtnClicked(position: Int) {
        val item = hotelList[position]
        hotelId = item.id
        //hotelId and roomItem needs to be passed here
        val action = PastBookingsFragmentDirections.actionPastBookingsFragment2ToHotelDescription2Fragment(hotelId)
        findNavController().navigate(action)
    }

    private fun getPastBookings(authToken: String){
        viewModel.getPastBooking(authToken)
    }

    private fun observeBookingHistoryFlow(){
        binding.pastBookingProgressBar.visibility = View.VISIBLE
        viewModel.getPastBookingLiveData.observe(viewLifecycleOwner, Observer {
            if (it.data.pageItems.isNotEmpty()){
                adapter.bookingList = it.data.pageItems
                binding.pastBookingProgressBar.visibility = View.GONE
                hotelList = it.data.pageItems
                adapter.notifyDataSetChanged()
            }else{
                binding.pastBookingProgressBar.visibility = View.GONE
                binding.noBookingTxt.text = "You have made no bookings"
            }
        })
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