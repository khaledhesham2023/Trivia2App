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
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.triviaapp2.R
import com.example.triviaapp2.data.model.AnswerItem
import com.example.triviaapp2.data.model.QuestionItem
import com.example.triviaapp2.databinding.FragmentHomeBinding
import com.example.triviaapp2.presentation.parent.ParentFragment
import com.example.triviaapp2.utils.MainNavigatorController
import com.example.triviaapp2.utils.MainNavigatorEvent
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
    private var isWinning = false
    private var isSolved = false
    private lateinit var listOfQuestions: ArrayList<QuestionItem>
    private val homeAdapter: HomeAdapter by lazy {
        HomeAdapter(requireContext(), ArrayList(), onRecycleNotified = {
            binding.answersList.post {
                homeAdapter.notifyItemChanged(it)
                configureAnswerButton()
            }
        }, onAnswerChecked = { answerItem, textView ->
            Log.i("Khaled", answerItem.toString())
            if (answerItem.isCorrect == true) {
                textView.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.bg_sheet_correct)
                binding.answerBtn.text = getString(R.string.continue_text)
                isWinning = true
                isSolved = true
            } else {
                textView.background =
                    ContextCompat.getDrawable(requireContext(), R.drawable.bg_sheet_incorrect)
                binding.answerBtn.text = getString(R.string.retry)
                isWinning = false
                isSolved = true
            }
            disableList(true)
        })
    }
    private val navArgs: HomeFragmentArgs by navArgs()
    private val mNavigator by lazy { MainNavigatorController(findNavController()) }

    override fun initializeComponents() {
        noOfQuestions = navArgs.noOfQuestions
        configureAnswerButton()
        setupClicks()
        observeGetQuestions()
        observeAllErrors()
        binding.answersList.adapter = homeAdapter
        homeViewModel.setEvent(QuestionsEvent.GetQuestions(noOfQuestions))
    }

    private fun configureAnswerButton() {
        binding.answerBtn.text = getString(R.string.select_answer)
        if (homeAdapter.isDisabled) {
            binding.answerBtn.apply {
                background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_btn_disabled)
                setTextColor(ContextCompat.getColor(requireContext(), R.color.dodger_blue))
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
                listOfQuestions = questionsList.toCollection(ArrayList())
                questionsList[currentQuestionIndex].category?.let { category ->
                    MainNavigatorEvent.HomeToSheet(
                        category
                    )
                }?.let { event -> mNavigator.setNavigationEvent(event) }
                configureQuestion(
                    requireContext(),
                    questionsList[currentQuestionIndex],
                    binding.questionNumberTv,
                    binding.difficulty,
                    binding.questionTv,
                    currentQuestionIndex,
                    noOfQuestions,
                    binding.background
                )
                val answers = arrayListOf<String>()
                questionsList[currentQuestionIndex].incorrectAnswers?.let { answers.addAll(it) }
                answers.shuffle()
                val answersList =
                    answers.map { answer ->
                        AnswerItem(
                            answer, false, questionsList[currentQuestionIndex].category,
                            if (questionsList[currentQuestionIndex].type.toString() == "boolean") {
                                answer.lowercase() == questionsList[currentQuestionIndex].correctAnswer
                            } else {
                                answer == questionsList[currentQuestionIndex].correctAnswer
                            }
                        )
                    }
                        .toCollection(ArrayList())
                Log.d("Khaled", answersList.toString())
                homeAdapter.updateAnswers(answersList)
                loadingDialog.dismiss()
            }
        }
    }

    private fun setupClicks() {
        binding.answerBtn.setOnClickListener {
            if (isSolved) {
                if (isWinning) {
                    isSolved = false
                    isWinning = false
                    Log.d("Khaled2",listOfQuestions[currentQuestionIndex].correctAnswer.toString())
                    if (currentQuestionIndex == noOfQuestions - 1) {
                        Toast.makeText(requireContext(), "End of questions", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        currentQuestionIndex++
                        configureQuestion(
                            requireContext(),
                            listOfQuestions[currentQuestionIndex],
                            binding.questionNumberTv,
                            binding.difficulty,
                            binding.questionTv,
                            currentQuestionIndex,
                            noOfQuestions,
                            binding.background
                        )
                        disableList(false)
                        homeAdapter.resetAll()
                        val answers = arrayListOf<String>()
                        listOfQuestions[currentQuestionIndex].incorrectAnswers?.let { answers.addAll(it) }
                        answers.shuffle()
                        val answersList =
                            answers.map { answer ->
                                AnswerItem(
                                    answer, false, listOfQuestions[currentQuestionIndex].category,
                                    if (listOfQuestions[currentQuestionIndex].type.toString() == "boolean") {
                                        answer.lowercase() == listOfQuestions[1].correctAnswer
                                    } else {
                                        answer == listOfQuestions[1].correctAnswer
                                    }
                                )
                            }
                                .toCollection(ArrayList())
                        Log.d("Khaled", listOfQuestions.toString())
                        homeAdapter.updateAnswers(ArrayList())
                        homeAdapter.updateAnswers(answersList)
                        listOfQuestions[currentQuestionIndex].category?.let { categoryItem ->
                            MainNavigatorEvent.HomeToSheet(
                                categoryItem
                            )
                        }?.let { event -> mNavigator.setNavigationEvent(event) }
                    }
                } else {
                    mNavigator.setNavigationEvent(MainNavigatorEvent.NavigateUp)
                }
            } else {
                homeAdapter.checkAnswer()
            }
        }
    }

    private fun disableList(b: Boolean) {
        if (b) {
            binding.view.apply {
                isVisible = true
                isClickable = true
            }
        } else {
            binding.view.apply {
                isVisible = false
                isClickable = false
            }
        }
    }
}

