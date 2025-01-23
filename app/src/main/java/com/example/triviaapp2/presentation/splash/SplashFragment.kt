package com.example.triviaapp2.presentation.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.triviaapp2.R
import com.example.triviaapp2.databinding.FragmentSplashBinding
import com.example.triviaapp2.presentation.parent.ParentFragment
import com.example.triviaapp2.utils.MainNavigatorController
import com.example.triviaapp2.utils.MainNavigatorEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : ParentFragment<FragmentSplashBinding>() {

    private val mNavigator: MainNavigatorController by lazy { MainNavigatorController(findNavController()) }

    override val layout: Int
        get() = R.layout.fragment_splash

    override fun initializeComponents() {
        Handler(Looper.getMainLooper()).postDelayed({
            mNavigator.setNavigationEvent(MainNavigatorEvent.SplashToMain)
        },3000L)
    }

}

