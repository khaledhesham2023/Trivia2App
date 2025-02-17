package com.example.triviaapp2.presentation.winning

import android.media.MediaPlayer
import androidx.navigation.fragment.findNavController
import com.example.triviaapp2.R
import com.example.triviaapp2.databinding.FragmentWinningBinding
import com.example.triviaapp2.presentation.parent.ParentFragment
import com.example.triviaapp2.utils.MainNavigatorController
import com.example.triviaapp2.utils.MainNavigatorEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WinningFragment : ParentFragment<FragmentWinningBinding>() {
    override val layout: Int
        get() = R.layout.fragment_winning

    private lateinit var congratulationsMediaPlayer: MediaPlayer
    private val navigator: MainNavigatorController by lazy { MainNavigatorController(findNavController()) }

    override fun initializeComponents() {
        congratulationsMediaPlayer = MediaPlayer.create(requireContext(),R.raw.congratulations)
        congratulationsMediaPlayer.start()
        setupClicks()
    }

    private fun setupClicks(){
        binding.returnBtn.setOnClickListener {
            navigator.setNavigationEvent(MainNavigatorEvent.WinningToStart)
        }
    }

}