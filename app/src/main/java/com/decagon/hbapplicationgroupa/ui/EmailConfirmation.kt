package com.decagon.hbapplicationgroupa.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.decagon.hbapplicationgroupa.R
import com.decagon.hbapplicationgroupa.databinding.FragmentEmailComfirmationBinding
import com.decagon.hbapplicationgroupa.viewModel.AuthViewModel

class EmailConfirmation : Fragment() {

    private var _binding: FragmentEmailComfirmationBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: AuthViewModel
    private val args: EmailConfirmationArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentEmailComfirmationBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(requireActivity())[AuthViewModel::class.java]
        val token = args.token
        val email = args.email
        if (email != null && token != null) {
            confirmEmail(email, token)
        }

        observeConfirmEmailLiveData()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.confirmationBtn.setOnClickListener {
            findNavController().navigate(R.id.action_emailConfirmation_to_loginFragment)
        }

        onBackPressed()
    }

    private fun confirmEmail (email: String, token: String){
        viewModel.confirmEmail(email, token)
    }

    private fun observeConfirmEmailLiveData(){
        viewModel.getConfirmEmailLiveData.observe(viewLifecycleOwner, Observer {
            if (it != null && it.succeeded){
                binding.emailConfirmationProgressBar.visibility = View.GONE
                binding.emailConfirmationSuccess.visibility = View.VISIBLE
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }else{
                binding.emailConfirmationProgressBar.visibility = View.GONE
                binding.emailConfirmationErrorText.text = "Confirmation Failed, kindly check your network"
            }
        })
    }

    fun onBackPressed() {
        //Overriding onBack press to finish activity and exit app
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_emailConfirmation_to_registerFragment)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

}