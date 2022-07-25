package com.decagon.hbapplicationgroupa.ui

import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import co.paystack.android.Paystack
import co.paystack.android.PaystackSdk
import co.paystack.android.Transaction
import co.paystack.android.exceptions.ExpiredAccessCodeException
import co.paystack.android.model.Card
import co.paystack.android.model.Charge
import com.decagon.hbapplicationgroupa.R
import com.decagon.hbapplicationgroupa.database.AuthPreference
import com.decagon.hbapplicationgroupa.databinding.FragmentPaymentDetailsBinding
import com.decagon.hbapplicationgroupa.model.hotelmodule.bookhotel.VerifyBooking
import com.decagon.hbapplicationgroupa.utils.ConnectivityLiveData
import com.decagon.hbapplicationgroupa.utils.CreditCardFormatter
import com.decagon.hbapplicationgroupa.viewModel.HotelViewModel
import com.decagon.hbapplicationgroupa.viewmodel.PaystackViewModel
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.platform.Jdk9Platform.Companion.isAvailable
import org.json.JSONException

@AndroidEntryPoint
class PaymentDetailsFragment : Fragment() {
    private var _binding: FragmentPaymentDetailsBinding? = null
    private val binding get() = _binding!!
    private val paystackViewModel: PaystackViewModel by viewModels()
    private val args: PaymentDetailsFragmentArgs by navArgs()
    private lateinit var cardNumber: EditText
    private lateinit var cardExpiryMonth: EditText
    private lateinit var cardExpiryYear: EditText
    private lateinit var cVC: EditText
    private var card: Card? = null
    private lateinit var connectivityLiveData: ConnectivityLiveData
    private var initTransactionId: String = ""
    private var transaction: Transaction? = null
    private var transactionReference: String? = null
    private var charge: Charge? = null
    private val hotelViewModel: HotelViewModel by viewModels()
    private var verificationDetails: VerifyBooking? = null
    private val publicTestKey = "pk_test_0e715dd3b6435c09f4d4bbc4b5f43150c3bf4b02"
    //"pk_test_515f61d7b258bbc0cfcd1367e2a62c1ca5d267b0"
    val email = "gbohunmimakinde@gmail.com"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaymentDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("XYZ", "onViewCreated: ${args.bookingReference}")
        binding.paymentDetailsPayButton.text = "Pay N${args.price}"

        connectivityLiveData = ConnectivityLiveData(requireActivity().application)
        //set public key
        PaystackSdk.setPublicKey(publicTestKey)

        cardNumber = binding.etCardNumber
        cardExpiryMonth = binding.etExpiry
        cardExpiryYear = binding.expYear
        cVC = binding.etCvv

//        if (cardNumber.text.isNotEmpty() && cardExpiryMonth.text.isNotEmpty() && cardExpiryYear.text.isNotEmpty() && cVC.text.isNotEmpty()) {
//            setUpPayment()
//        }
        addTextWatcherToEditText()
        handleOnSubmitClick()

       // pushPaymentVerificationDetails()

    }

//    private fun initializePaystack() {
//        PaystackSdk.initialize(requireActivity().applicationContext)      //initialize paystack
//                 //set public key
//    }

    private fun addTextWatcherToEditText(){

        //Make button UnClickable for the first time
        binding.paymentDetailsPayButton.isEnabled = false

        //make the button clickable after detecting changes in input field
        val watcher: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                val s1 = cardNumber.text.toString()
                val s2 = cardExpiryMonth.text.toString()
                val s3 = cardExpiryYear.text.toString()
                val s4 = cVC.text.toString()

                //check if they are empty, make button unclickable
                if (s1.isEmpty() || s2.isEmpty() || s3.isEmpty() || s4.isEmpty()) {
                    binding.paymentDetailsPayButton.isEnabled = false
                }


                //check the length of all edit text, if meet the required length, make button clickable
                if (s1.length >= 16 && s2.length == 2 && s3.length == 2 && s4.length == 3) {
                    binding.paymentDetailsPayButton.isEnabled = true
                }

                //if edit text doesn't meet the required length, make button unclickable. You could use else statement from the above if
                if (s1.length < 16 || s2.length < 2 || s3.length < 2 || s4.length < 3) {
                    binding.paymentDetailsPayButton.isEnabled = false
                }

            }

            override fun afterTextChanged(s: Editable) {

            }
        }

        //add text watcher
        cardNumber.addTextChangedListener(CreditCardFormatter()) //helper class for card number formatting
        cardExpiryMonth.addTextChangedListener(watcher)
        cardExpiryYear.addTextChangedListener(watcher)
        cVC.addTextChangedListener(watcher)


    }

    private fun validateCard(): Card {

        //validate fields

        val cardNumberValidate = cardNumber.text.toString().trim()
        val cardExpiryMonthValidate = cardExpiryMonth.inputType
        val cardExpiryYearValidate = cardExpiryYear.inputType
        val cvcValidate = cVC.text.toString().trim()

        //formatted values
        val cardNumberWithoutSpace = cardNumberValidate.replace(" ", "")

        //build card object with ONLY the number, update the other fields later
        val card: Card = Card.Builder(cardNumberWithoutSpace, 0, 0, "").build()

        //update the cvc field of the card
        card.cvc = cvcValidate
        card.expiryMonth = cardExpiryMonthValidate
        card.expiryYear = cardExpiryYearValidate

        return card

    }


    // This sets up the card and check for validity
//    private fun setUpPayment() {
//         card = Card(cardNumber.text.toString(), cardExpiryMonth.inputType, cardExpiryYear.inputType, cVC.text.toString())
//
//        if (card!!.isValid) {
//            //charge card
//            binding.paymentDetailsSubmitButton.enable(true)
//
//        } else {
//            //handle error
//            binding.paymentDetailsSubmitButton.enable(false)
//            Toast.makeText(requireContext(), "Invalid card details", Toast.LENGTH_SHORT).show()
//
//        }
//    }

    //charge card and handle callbacks
    private fun performCharge() {
        binding.paymentDetailsProgressBar.visibility = View.VISIBLE
        val charge = Charge()
        charge.card = validateCard()     //set card to charge
        try {
            charge.amount = (args.price * 100).toInt()
            Log.d("XYZ", "performChargeAmt1: $charge.amount")
        }catch (e: Exception) {
            Log.d("XYZ", "performChargeAmt2: $charge.amount")
        }
        charge.email = email
        charge.reference = args.bookingReference
        try {
            charge!!.putCustomField("Charged From", "Android SDK")
        }catch (e: JSONException) {
            e.printStackTrace()
        }

        PaystackSdk.chargeCard(requireActivity(), charge, object: Paystack.TransactionCallback {
            override fun onSuccess(transaction: Transaction?) {
                // This is called only after transaction is deemed successful.
                // Retrieve the transaction, and send its reference to your server
                // for verification.
                binding.paymentDetailsProgressBar.visibility = View.GONE
                transactionReference = transaction?.reference
                Log.d("XYZ", "performChargeref: ${transaction?.reference}")
                Log.d("XYZ", "performChargeref: $transactionReference")
                pushPaymentVerificationDetails()
            }

            override fun beforeValidate(transaction: Transaction?) {
                // This is called only before requesting OTP.
                // Save reference so you may send to server. If
                // error occurs with OTP, you should still verify on server.

                transactionReference = transaction?.reference
            }

            override fun onError(error: Throwable?, transaction: Transaction?) {
                //handle error here

                //stop loading
                binding.paymentDetailsProgressBar.visibility = View.GONE
                binding.paymentDetailsPayButton.visibility =  View.VISIBLE
                when {
                    error is ExpiredAccessCodeException -> {
                        this@PaymentDetailsFragment.performCharge()
                        findNavController().navigate(R.id.action_paymentDetailsFragment_to_paymentCheckoutFragment)
                    }

                    transaction!!.reference != null -> {

                        Toast.makeText(requireContext(), error!!.message?:"", Toast.LENGTH_LONG).show()
                        findNavController().navigate(R.id.action_paymentDetailsFragment_to_paymentCheckoutFragment)

                        Log.d("XYZ", "onErrorTrRef: ${error!!.message}")

                        //also you can do a verification on your backend server here

                    }
                    else -> {

                        Toast.makeText(requireContext(), error!!.message?:"", Toast.LENGTH_LONG).show()
                        Log.d("XYZ", "onErrorOfError: ${error!!.message}")
                        findNavController().navigate(R.id.action_paymentDetailsFragment_to_paymentCheckoutFragment)

                    }
                }
            }

        })
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun handleOnSubmitClick() {
        //on click of the pay button
        binding.paymentDetailsPayButton.setOnClickListener {
            //show loading
            binding.paymentDetailsProgressBar.visibility = View.VISIBLE
            binding.paymentDetailsPayButton.visibility = View.GONE

            //first, check for network availability
            connectivityLiveData.observe(viewLifecycleOwner, {
                when(isAvailable) {
                    true -> {
                        //process payment
                        performCharge()
                    }
                    false -> {
                        Toast.makeText(requireContext(), "Network error. Please, check your network connection", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }
//    private fun initTransactionResponse() {
//        val payAuthToken = "Bearer $publicTestKey"
//        val paymentInfo = InitializeTransaction(email, args.price.toString(), args.bookingReference)
//        paystackViewModel.getInitResponse(payAuthToken, paymentInfo)
//        paystackViewModel.initResponse.observe(viewLifecycleOwner, {
//            if (it != null) {
//                initTransactionId =it.data.reference
//            }
//        })
//
//    }

    private fun pushPaymentVerificationDetails() {
        val authToken = "Bearer ${AuthPreference.getToken(AuthPreference.TOKEN_KEY)}"
                verificationDetails = VerifyBooking("", transactionReference!!)
        hotelViewModel.pushPaymentTransactionDetails(authToken, verificationDetails!!)
        hotelViewModel.bookingVerificationDetails.observe(viewLifecycleOwner, {
            if (it != null && it.message == "Booking Verified") {
                Toast.makeText(requireContext(), "Payment Successful!", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_paymentDetailsFragment_to_bookingConfirmationFragment)
            } else {
                Toast.makeText(
                    requireContext(),
                    "Unsuccessful. Please try again ",
                    Toast.LENGTH_LONG
                ).show()
            }
        })
    }

    override fun onPause() {
        super.onPause()
        binding.paymentDetailsProgressBar.visibility = View.GONE
    }
}