package com.example.triviaapp2.presentation.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.triviaapp2.data.model.GetQuestionsResponse
import com.example.triviaapp2.data.model.QuestionItem
import com.example.triviaapp2.domain.usecases.GetQuestionsUseCases
import com.example.triviaapp2.utils.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val questionsUseCases: GetQuestionsUseCases
) : ViewModel() {

    private var _questions = MutableSharedFlow<List<QuestionItem>>()
    val questions = _questions.asSharedFlow()

    private var _error = MutableSharedFlow<NetworkResponse<GetQuestionsResponse>>()
    val error = _error.asSharedFlow()

    fun setEvent(event: QuestionsEvent){
        when(event){
            is QuestionsEvent.GetQuestions -> handleGetQuestions(event.noOfQuestions)
        }
    }


    private fun handleGetQuestions(noOfQuestions: Int) = viewModelScope.launch {
        Log.d("Success","handleGetQuestions")
        questionsUseCases.invoke(noOfQuestions).collectLatest { response ->
            when (response) {
                is NetworkResponse.Success -> {
                    response.data?.results?.let { _questions.emit(it) }
                }
                else -> {
                    _error.emit(response)
                }
            }
        }
    }
}

sealed class QuestionsEvent {
    data class GetQuestions(val noOfQuestions: Int): QuestionsEvent()
}