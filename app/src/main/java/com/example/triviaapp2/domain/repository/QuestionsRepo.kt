package com.example.triviaapp2.domain.repository

import com.example.triviaapp2.data.model.GetQuestionsResponse
import com.example.triviaapp2.data.model.QuestionItem
import com.example.triviaapp2.utils.NetworkResponse
import kotlinx.coroutines.flow.Flow

interface QuestionsRepo {
    suspend fun getQuestions(numberOfQuestions: Int): Flow<NetworkResponse<GetQuestionsResponse>>
}