package com.decagon.hbapplicationgroupa.ui

import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.decagon.hbapplicationgroupa.R
import com.decagon.hbapplicationgroupa.database.AuthPreference
import com.decagon.hbapplicationgroupa.databinding.FragmentSplashScreenBinding
import com.decagon.hbapplicationgroupa.viewModel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : Fragment() {
    private var _binding: FragmentSplashScreenBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AuthViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        scaleText()
        navigateToNextFragment()
    }

    //Create function that animates text in splash screen view
    private fun scaleText() {
        val appNameView = binding.fragmentSplashScreenHotelVoyageVw
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 4f)
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 4f)
        val animator = ObjectAnimator.ofPropertyValuesHolder(appNameView, scaleX, scaleY)

        animator.duration = 3000
        animator.repeatCount = 1
        animator.repeatMode = ObjectAnimator.REVERSE
        animator.start()
    }

    //create function that moves to the next fragment using sharePreference
    private fun navigateToNextFragment(){
        AuthPreference.initPreference(requireActivity())
        val handler = Handler(Looper.getMainLooper())
        val userId = AuthPreference.getId("id_key")
        val authToken = AuthPreference.getToken("token_key")
        if (userId == null){
            handler.postDelayed({
                findNavController().navigate(R.id.action_splashScreenFragment_to_onboarding01Fragment2)
            }, 3000)
        }else{
            if (authToken == null){
                handler.postDelayed({
                    findNavController().navigate(R.id.action_splashScreenFragment_to_loginFragment)
                }, 3000)
            }else{
                handler.postDelayed({
                    findNavController().navigate(R.id.action_splashScreenFragment_to_exploreHomeFragment)
                }, 3000)
            }
        }
    }
}