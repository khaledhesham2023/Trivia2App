package com.example.triviaapp2.data.model

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import java.util.ArrayList

@Keep
data class QuestionItem(
    val type: Boolean? = null,
    val difficulty: String? = null,
    val category: String? = null,
    val question:String? = null,
    @SerializedName("correct_answer")
    val correctAnswer: String? = null,
    @SerializedName("incorrect_answers")
    val incorrectAnswers: ArrayList<String>? = null
)