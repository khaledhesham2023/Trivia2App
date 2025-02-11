package com.example.triviaapp2.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class QuestionItem(
    val type: Boolean? = null,
    val difficulty: Boolean? = null,
    val category: String? = null,
    val question:String? = null,
    @SerializedName("correct_answer")
    val correctAnswer: String? = null,
    @SerializedName("incorrect_answers")
    val incorrectAnswers: List<String>? = null
)