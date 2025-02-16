package com.example.triviaapp2.presentation.home

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.triviaapp2.R
import com.example.triviaapp2.data.model.AnswerItem
import com.example.triviaapp2.databinding.FragmentHomeBinding
import com.example.triviaapp2.presentation.parent.ParentFragment
import com.example.triviaapp2.utils.NetworkResponse
import com.example.triviaapp2.utils.configureQuestion
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : ParentFragment<FragmentHomeBinding>() {
    override val layout: Int
        get() = R.layout.fragment_home

    private val homeViewModel: HomeViewModel by viewModels()
    private var currentQuestionIndex = 0
    private var noOfQuestions = 0
    private val homeAdapter: HomeAdapter by lazy { HomeAdapter(requireContext(), ArrayList()){
        binding.answersList.post {
            homeAdapter.notifyItemChanged(it)
            configureAnswerButton()
        }
    } }
    private val navArgs: HomeFragmentArgs by navArgs()

    override fun initializeComponents() {
        noOfQuestions = navArgs.noOfQuestions
        configureAnswerButton()
        setupClicks()
        observeGetQuestions()
        observeAllErrors()
        binding.answersList.adapter = homeAdapter
        homeViewModel.setEvent(QuestionsEvent.GetQuestions(5))
    }

    private fun configureAnswerButton() {
        if (homeAdapter.isDisabled){
            binding.answerBtn.apply {
                background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_btn_disabled)
                setTextColor(ContextCompat.getColor(requireContext(),R.color.dodger_blue))
                isClickable = false
            }
        } else {
            binding.answerBtn.apply {
                background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_btn)
                setTextColor(Color.WHITE)
                isClickable = true
            }
        }

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
                Log.d(
                    "Khaled",
                    "${questionsList[0].category} ${questionsList[0].correctAnswer} ${questionsList[0].difficulty}"
                )
                configureQuestion(
                    requireContext(),
                    questionsList[0],
                    binding.questionNumberTv,
                    binding.difficulty,
                    binding.questionTv,
                    currentQuestionIndex,
                    noOfQuestions,
                    binding.background
                )
                val answers = arrayListOf<String>()
                questionsList[0].incorrectAnswers?.let { answers.addAll(it) }
                answers.shuffle()
                val answersList =
                    answers.map { answer -> AnswerItem(answer, false) }.toCollection(ArrayList())
                homeAdapter.updateAnswers(answersList)
                loadingDialog.dismiss()
                Toast.makeText(requireContext(), questionsList.size.toString(), Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }
    private fun setupClicks() {
    binding.answerBtn.setOnClickListener {

    }
    }
}

