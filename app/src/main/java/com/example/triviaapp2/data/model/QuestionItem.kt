package com.example.triviaapp2.data.model

import androidx.annotation.Keep

@Keep
data class QuestionItem(
    val type: Boolean? = null,
    val difficulty: Boolean? = null,
    val category: String? = null,
    val question:String? = null,
    val correct_answer: String? = null,
    val incorrect_answers: List<String>? = null
)