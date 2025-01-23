package com.example.triviaapp2.data.repository

import com.example.triviaapp2.data.model.QuestionItem
import com.example.triviaapp2.data.network.ServicesApi
import com.example.triviaapp2.domain.repository.QuestionsRepo
import com.example.triviaapp2.utils.NetworkResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QuestionsRepoImpl @Inject constructor(
    private val api: ServicesApi
) : QuestionsRepo {
    override suspend fun getQuestions(amount: Int): Flow<NetworkResponse<List<QuestionItem>>> = flow {
        emit(NetworkResponse.Loading())
        when(val response = api.getQuestions(amount)){
            is NetworkResponse.Success -> emit(NetworkResponse.Success(newData = response.newData?.results))
            else -> emit(NetworkResponse.Error(errorMessage = response.message))
        }
    }
}