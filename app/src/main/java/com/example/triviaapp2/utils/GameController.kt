package com.example.triviaapp2.utils

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.text.Html
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.example.triviaapp2.R
import com.example.triviaapp2.data.model.QuestionItem
import com.example.triviaapp2.utils.enums.Categories
import com.example.triviaapp2.utils.enums.Difficulty

fun configureQuestion(
    context: Context,
    question: QuestionItem,
    currentQuestionTv: AppCompatTextView,
    difficultyIv: AppCompatImageView,
    questionTv: AppCompatTextView,
    questionNumber: Int,
    noOfQuestions: Int,
    background: AppCompatImageView
) {
    val answersList = question.incorrectAnswers
    question.correctAnswer?.let { answersList?.add(it) }
    answersList?.shuffle()
    currentQuestionTv.text =
        context.getString(R.string.question_no, (questionNumber + 1).toString(), noOfQuestions.toString())

    val icon = configureDifficulty(question.difficulty)
    difficultyIv.setImageResource(icon)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        questionTv.text = Html.fromHtml(question.question,Html.FROM_HTML_MODE_LEGACY).toString()
    } else {
        questionTv.text = Html.fromHtml(question.question).toString()
    }
//    background.background = (when(question.category){
//        Categories.ANIMALS.name -> ColorDrawable(ContextCompat.getColor(context,R.color.forest_green))
//        Categories.ART.name -> ColorDrawable(ContextCompat.getColor(context,R.color.magenta))
//        Categories.CELEBRITIES.name -> ColorDrawable(ContextCompat.getColor(context,R.color.gold))
//        Categories.ENTERTAINMENT_ANIME_MANGA.name -> ColorDrawable(ContextCompat.getColor(context,R.color.deep_pink))
//        Categories.ENTERTAINMENT_BOARD_GAMES.name -> ColorDrawable(ContextCompat.getColor(context,R.color.red))
//        Categories.ENTERTAINMENT_BOOKS.name -> ColorDrawable(ContextCompat.getColor(context,R.color.brown))
//        Categories.ENTERTAINMENT_CARTOON_ANIMATIONS.name -> ColorDrawable(ContextCompat.getColor(context,R.color.orange_red))
//        Categories.ENTERTAINMENT_COMICS.name -> ColorDrawable(ContextCompat.getColor(context,R.color.gold))
//        Categories.ENTERTAINMENT_FILMS.name -> ColorDrawable(ContextCompat.getColor(context,R.color.black))
//        Categories.ENTERTAINMENT_MUSIC.name -> ColorDrawable(ContextCompat.getColor(context,R.color.purple))
//        Categories.ENTERTAINMENT_MUSICALS_THEATRES.name -> ColorDrawable(ContextCompat.getColor(context,R.color.gold))
//        Categories.ENTERTAINMENT_TV.name -> ColorDrawable(ContextCompat.getColor(context,R.color.dodger_blue))
//        Categories.ENTERTAINMENT_VIDEO_GAMES.name -> ColorDrawable(ContextCompat.getColor(context,R.color.arcade_green))
//        Categories.GENERAL_KNOWLEDGE.name -> ColorDrawable(ContextCompat.getColor(context,R.color.white))
//        Categories.GEOGRAPHY.name -> ColorDrawable(ContextCompat.getColor(context,R.color.teal))
//        Categories.HISTORY.name -> ColorDrawable(ContextCompat.getColor(context,R.color.sepia))
//        Categories.MYTHOLOGY.name -> ColorDrawable(ContextCompat.getColor(context,R.color.bronze))
//        Categories.POLITICS.name -> ColorDrawable(ContextCompat.getColor(context,R.color.navy_blue))
//        Categories.SCIENCE_AND_NATURE.name -> ColorDrawable(ContextCompat.getColor(context,R.color.nature_green))
//        Categories.SCIENCE_COMPUTERS.name -> ColorDrawable(ContextCompat.getColor(context,R.color.dark_turquoise))
//        Categories.SCIENCE_GADGETS.name -> ColorDrawable(ContextCompat.getColor(context,R.color.light_steel_blue))
//        Categories.SCIENCE_MATHEMATICS.name -> ColorDrawable(ContextCompat.getColor(context,R.color.dark_blue))
//        Categories.SPORTS.name -> ColorDrawable(ContextCompat.getColor(context, R.color.dark_orange))
//        Categories.VEHICLES.name -> ColorDrawable(ContextCompat.getColor(context, R.color.silver))
//        else -> ColorDrawable(ContextCompat.getColor(context,R.color.white))
//    })

}

fun configureDifficulty(difficulty: String?): Int {
    return when(difficulty){
        Difficulty.EASY.value -> R.drawable.ic_easy
        Difficulty.MEDIUM.value -> R.drawable.ic_medium
        else -> R.drawable.ic_difficult
    }

}

fun prepareNextQuestion() {

}