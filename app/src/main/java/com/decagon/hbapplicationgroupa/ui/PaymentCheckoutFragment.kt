package com.decagon.hbapplicationgroupa.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.decagon.hbapplicationgroupa.database.AuthPreference
import com.decagon.hbapplicationgroupa.databinding.FragmentPaymentCheckoutBinding
import com.decagon.hbapplicationgroupa.model.hotelmodule.bookhotel.BookHotel
import com.decagon.hbapplicationgroupa.model.hotelmodule.bookhotel.VerifyBooking
import com.decagon.hbapplicationgroupa.utils.findNumberOfDays
import com.decagon.hbapplicationgroupa.viewModel.HotelViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PaymentCheckoutFragment : Fragment() {
    //Set up view binding here
    private var _binding: FragmentPaymentCheckoutBinding? = null
    private val binding get() = _binding!!
    private val hotelViewModel: HotelViewModel by viewModels()
    private val args: PaymentCheckoutFragmentArgs by navArgs()
    private var price = 0.0F
    private var bookingReference = ""
    private lateinit var verificationDetails: VerifyBooking
    private lateinit var hotelBookingInfo: BookHotel
    private var selectedPaymentOption = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Enabled view binding here
        _binding = FragmentPaymentCheckoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val checkInDate = args.checkIn
        val checkOutDate = args.checkOut
        val numberOfDays = findNumberOfDays(checkOutDate, checkInDate)
        val priceDiscount = (args.price - (args.price * 0.1))

        val price = when {
            numberOfDays > 1 -> "${priceDiscount * numberOfDays}"
            else -> "${args.price * numberOfDays}"
        }
        binding.paymentOptionAmountTv.text = "N$price"

        val navToPaystack = binding.paymentOptionPaystack
//        val navToFlutterwave = binding.paymentOptionFlutterwave

        if (navToPaystack.isPressed) {
            selectedPaymentOption = binding.paymentOptionPaystackTv.text.toString()
        }
        //back arrow navigation
        val backArrow = binding.paymentOptionBackBtn
        backArrow.setOnClickListener {
            findNavController().popBackStack()
        }


        //Visa navigation
        //val navToVisa = binding.paymentOptionGpay
        navToPaystack.setOnClickListener {
            pushBookHotelData()
            Toast.makeText(requireContext(), "Redirecting to Paystack", Toast.LENGTH_SHORT).show()
//            findNavController().navigate(R.id.action_paymentCheckoutFragment_to_paymentDetailsFragment)
        }
        onBackPressed()
    }

    //Method to handle back press
    private fun onBackPressed() {
        //Overriding onBack press to navigate to home Fragment onBack Pressed
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    private fun pushBookHotelData() {
        val authToken = "Bearer ${AuthPreference.getToken(AuthPreference.TOKEN_KEY)}"
        hotelBookingInfo = BookHotel(
            args.roomId,
            args.checkIn,
            args.checkOut,
            args.numberOfPeople,
            "paystack"
        )
        Log.d("XYZ", "pushBookHotelData: ${args.roomId}")
        Log.d("XYZ", "pushBookHotelDatapaymentoption: $selectedPaymentOption ")
        hotelViewModel.pushBookHotel(authToken, hotelBookingInfo!!)
        hotelViewModel.bookingInfo.observe(viewLifecycleOwner, {
            if (it != null) {
                price = it.data.price.toFloat()
//                    transactionURL = it.data.paymentUrl
                bookingReference = it.data.paymentReference
                Log.d(
                    "XYZ",
                    "pushBookHotelDataroomIdpaymentreference: ${it.data.paymentReference} "
                )
            }
            val action =
                PaymentCheckoutFragmentDirections.actionPaymentCheckoutFragmentToPaymentDetailsFragment(
                    bookingReference,
                    price
                )
            findNavController().navigate(action)
        })
    }
}