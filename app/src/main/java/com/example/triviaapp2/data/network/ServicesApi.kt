package com.example.triviaapp2.data.network

import com.example.triviaapp2.data.model.GetQuestionsResponse
import com.example.triviaapp2.utils.NetworkResponse
import com.example.triviaapp2.utils.Urls
import retrofit2.http.GET
import retrofit2.http.Query

interface ServicesApi {
    @GET(value = Urls.GET_NO_OF_QUESTIONS)
    suspend fun getQuestions(@Query("amount") amount: Int): NetworkResponse<GetQuestionsResponse>
}