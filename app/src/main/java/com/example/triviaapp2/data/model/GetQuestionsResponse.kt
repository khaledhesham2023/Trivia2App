package com.example.triviaapp2.data.model

import androidx.annotation.Keep

@Keep
data class GetQuestionsResponse(
    val response_code: Int,
    val results: List<QuestionItem>
)