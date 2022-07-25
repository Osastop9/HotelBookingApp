package com.decagon.hbapplicationgroupa.ui

import android.annotation.SuppressLint
import android.os.Bundle
 import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
  import androidx.navigation.fragment.findNavController
 import com.decagon.hbapplicationgroupa.R
import com.decagon.hbapplicationgroupa.databinding.FragmentForgotPasswordBinding
import com.decagon.hbapplicationgroupa.utils.ValidateEmail
import com.decagon.hbapplicationgroupa.viewModel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordFragment : Fragment () {
    private var _binding: FragmentForgotPasswordBinding? = null
    private val binding get() = _binding!!

    val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentForgotPasswordBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnForgotPassword.setOnClickListener {
            val userEmail = binding.tvEmailTextForgotpassword.text.toString().trim()

            // check if the email is valid email address
            if (!ValidateEmail.isEmailValid(userEmail)){
                binding.tvEmailTextForgotpassword.error = "please enter a valid email"
            }else{
                viewModel.sendForgortPasswordEmail(userEmail)
                viewModel.forgotPasswordEmail.observe(viewLifecycleOwner, Observer {
                    val response = viewModel.forgotPasswordEmail.value
                    if (response?.data == null) {
                        "Account does not exist".also { binding.tvForgotPasswordFamilyText.text = it }
                        binding.forgotPasswordResponseTv.visibility = View.GONE
                    }  else{
                        binding.forgotPasswordResponseTv.text = " A link has been sent to ${response.data} check your mail to reset password"
                    }
                })
            }
        }

        binding.tvForgotPasswordLoginText.setOnClickListener {
            findNavController().navigate(R.id.action_forgotPasswordFragment_to_loginFragment)
        }

        onBackPressed()
    }

    fun onBackPressed(){
        //Overriding onBack press to handle back press
        val callback = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }
}