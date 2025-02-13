package com.example.triviaapp2.presentation.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.triviaapp2.R
import com.example.triviaapp2.databinding.FragmentHomeBinding
import com.example.triviaapp2.presentation.parent.ParentFragment
import com.example.triviaapp2.utils.NetworkResponse
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : ParentFragment<FragmentHomeBinding>() {
    override val layout: Int
        get() = R.layout.fragment_home

    private val homeViewModel: HomeViewModel by viewModels()
    private var currentQuestionIndex = 0
    private var lastQuestionIndex = 0
    override fun initializeComponents() {
        setupClicks()
        observeGetQuestions()
        observeAllErrors()
        homeViewModel.setEvent(QuestionsEvent.GetQuestions(5))
    }

    private fun observeAllErrors() {
        viewLifecycleOwner.lifecycleScope.launch {
            homeViewModel.error.collect { state ->
                when (state) {
                    is NetworkResponse.Loading -> {
                        Log.d("TAGGG", "Loading")
                        loadingDialog.show()
                    }

                    else -> {
                        Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT)
                            .show()
                        loadingDialog.dismiss()

                    }
                }
            }
        }
    }

    private fun observeGetQuestions() {
        lifecycleScope.launch {
            homeViewModel.questions.collect { questionsList ->
                lastQuestionIndex = questionsList.size - 1
                loadingDialog.dismiss()
                Toast.makeText(requireContext(), questionsList.size.toString(), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
}

private fun setupClicks() {

}