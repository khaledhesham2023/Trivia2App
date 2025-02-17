package com.example.triviaapp2.data.model

data class AnswerItem(
    val answer: String? = null,
    var isSelected: Boolean = false,
    val category:String? = null,
    val isCorrect: Boolean? = null
)