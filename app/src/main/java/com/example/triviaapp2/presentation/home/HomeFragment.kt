package com.example.triviaapp2.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.triviaapp2.R
import com.example.triviaapp2.databinding.FragmentHomeBinding
import com.example.triviaapp2.presentation.parent.ParentFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : ParentFragment<FragmentHomeBinding>() {
    override val layout: Int
        get() = R.layout.fragment_home

    private val homeViewModel : HomeViewModel by viewModels()

    override fun initializeComponents() {
        setupClicks()
        observeGetQuestions()
        observeAllErrors()

    }

    private fun observeAllErrors() {
        lifecycleScope.launchWhenCreated {
            homeViewModel.error.collect { error ->

            }
        }
    }

    private fun observeGetQuestions() {
        lifecycleScope.launch {
            homeViewModel.questions.collect { questionsList ->

            }
        }
    }

    private fun setupClicks() {

    }

}