package com.example.triviaapp2.data.repository

import com.example.triviaapp2.data.model.GetQuestionsResponse
import com.example.triviaapp2.data.network.ServicesApi
import com.example.triviaapp2.domain.repository.QuestionsRepo
import com.example.triviaapp2.utils.NetworkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QuestionsRepoImpl @Inject constructor(
    private val api: ServicesApi
) : QuestionsRepo {
    override suspend fun getQuestions(numberOfQuestions: Int): Flow<NetworkResponse<GetQuestionsResponse>> =
        flow {
            emit(NetworkResponse.Loading())
            try {
                val response = api.getQuestions(numberOfQuestions)
                emit(NetworkResponse.Success(response))
            } catch (e: Exception) {
                emit(NetworkResponse.Error(e.message))
            }
        }
}