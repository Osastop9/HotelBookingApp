package com.decagon.hbapplicationgroupa.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.decagon.hbapplicationgroupa.R
import com.decagon.hbapplicationgroupa.*
import com.decagon.hbapplicationgroupa.databinding.FragmentResetPasswordBinding
import com.decagon.hbapplicationgroupa.viewModel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResetPasswordFragment : Fragment() {
    private var _binding: FragmentResetPasswordBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AuthViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResetPasswordBinding.inflate(inflater, container, false)
        return binding.root

    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val token = arguments?.getString("token")
        val email = arguments?.getString("email")

        //method to display hint a user on the password to input
        binding.tvEmailTextResetPassword.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus){
                binding.tvCheckEmptyNewPasswordResetPassword.text = "Must contain one uppercase,lowercase letter,digit and special character"
                binding.tvCheckEmptyNewPasswordResetPassword.setTextColor(resources.getColor(R.color.custom_green_color))
                binding.tvCheckEmptyNewPasswordResetPassword.visibility = View.VISIBLE
            }else{
                binding.tvCheckEmptyNewPasswordResetPassword.visibility = View.INVISIBLE
            }
        }

        //method to set textView to invisible while the view is not visible
        binding.tvConfirmPasswordResetPassword.setOnFocusChangeListener {_, hasFocus ->
            if (!hasFocus){
                binding.tvCheckEmptyConfirmPasswordResetPassword.visibility = View.INVISIBLE
            }
        }

        binding.tvResetPasswordLoginText.setOnClickListener {
            findNavController().navigate(R.id.action_resetPasswordFragment_to_loginFragment)
        }
        // button to navigate to login fragment after password reset
        binding.btnResetPassword.setOnClickListener {

            val newPassword = binding.tvEmailTextResetPassword.text.toString()
            val confirmPassword = binding.tvConfirmPasswordResetPassword.text.toString()


            if (validateNotEmptyNewPasswordField(newPassword)
                && validateNewPassword(newPassword)
                &&  validateNewPasswordAndConfirmPassword(newPassword, confirmPassword)

          ){
                binding.fragmentResetPasswordProgressBarPb.visibility = View.VISIBLE
                binding.tvCheckEmptyNewPasswordResetPassword.visibility = View.VISIBLE
                binding.btnResetPassword.text = "Resetting"
               makeApiCall(token!!,email!!, newPassword,confirmPassword)

            }else{

                if (!validateNotEmptyNewPasswordField(newPassword)){
                    binding.tvCheckEmptyNewPasswordResetPassword.text = "Please enter your new password"
                    binding.tvCheckEmptyNewPasswordResetPassword.visibility = View.VISIBLE
                    binding.tvCheckEmptyNewPasswordResetPassword.setTextColor(resources.getColor(R.color.custom_orange_color))
                }
                if(!validateNewPassword(newPassword) ){
                    binding.tvCheckEmptyNewPasswordResetPassword.text = "Please enter valid password"
                }

                if (validateNewPassword(newPassword) && !validateNewPasswordAndConfirmPassword(newPassword,confirmPassword)){
                    binding.tvCheckEmptyConfirmPasswordResetPassword.text = "Password does not match"
                    binding.tvCheckEmptyConfirmPasswordResetPassword.visibility = View.VISIBLE
                }

            }

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



    // making Api call to reset user's password
    fun makeApiCall( token: String,  email: String, newPassword: String, confirmPassword: String){
        viewModel.resetPasswordLiveData.observe(requireActivity(),{
            if (it == null){
                Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
                binding.fragmentResetPasswordProgressBarPb.visibility = View.GONE
                binding.btnResetPassword.text = "Reset Password"
            }else{
                findNavController().navigate(R.id.action_resetPasswordFragment_to_loginFragment)
                binding.fragmentResetPasswordProgressBarPb.visibility = View.GONE
                binding.btnResetPassword.text = "Reset Password"
            }
        })
        viewModel.resetUserPassword(token,email, newPassword, confirmPassword)
    }



}