package com.example.triviaapp2.domain.usecases

import com.example.triviaapp2.domain.repository.QuestionsRepo
import javax.inject.Inject

class GetQuestionsUseCases @Inject constructor(
    private val questionsRepo: QuestionsRepo
){
    suspend fun invoke(amount: Int) = questionsRepo.getQuestions(amount)
}