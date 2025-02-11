package com.example.triviaapp2.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private var _error = MutableSharedFlow<String>()
    val error = _error.asSharedFlow()

    fun setEvent(event: QuestionsEvent){
        when(event){
            is QuestionsEvent.GetQuestions -> handleGetQuestions(event.noOfQuestions)
        }
    }


    private fun handleGetQuestions(noOfQuestions: Int) = viewModelScope.launch {
        questionsUseCases.invoke(noOfQuestions).collectLatest { response ->
            when (response) {
                is NetworkResponse.Success -> response.data?.let { responseModel -> _questions.emit(responseModel.results) }
                else -> response.message?.let { message -> _error.emit(message) }
            }
        }
    }

}

sealed class QuestionsEvent {
    data class GetQuestions(val noOfQuestions: Int): QuestionsEvent()
}