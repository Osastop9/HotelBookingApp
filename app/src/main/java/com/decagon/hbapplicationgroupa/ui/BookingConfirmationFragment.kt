package com.decagon.hbapplicationgroupa.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.decagon.hbapplicationgroupa.R
import com.decagon.hbapplicationgroupa.databinding.FragmentBookingConfirmationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookingConfirmationFragment : Fragment() {
    //Set up view binding here
    private var _binding: FragmentBookingConfirmationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        //Enabled view binding here
        _binding = FragmentBookingConfirmationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Set appropriate navigation here when the goBackToHomeButton is clicked
        binding.goBackToHomeButton.setOnClickListener {
            findNavController().navigate(R.id.action_bookingConfirmationFragment_to_exploreHomeFragment)
        }
    }
}