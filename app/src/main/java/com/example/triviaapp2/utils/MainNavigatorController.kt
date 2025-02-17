package com.example.triviaapp2.utils

import androidx.navigation.NavController
import com.example.triviaapp2.presentation.home.HomeFragmentDirections
import com.example.triviaapp2.presentation.splash.SplashFragmentDirections
import com.example.triviaapp2.presentation.start.StartFragmentDirections

class MainNavigatorController(private val navController: NavController) {

    fun setNavigationEvent(event: MainNavigatorEvent) {
        when (event) {
            is MainNavigatorEvent.SplashToStart -> {
                navController.navigate(SplashFragmentDirections.actionSplashFragmentToStartFragment())
            }

            is MainNavigatorEvent.StartToHome -> {
                navController.navigate(
                    StartFragmentDirections.actionStartFragmentToHomeFragment(
                        event.noOfQuestions
                    )
                )
            }

            is MainNavigatorEvent.HomeToSheet -> {
                navController.navigate(
                    HomeFragmentDirections.actionHomeFragmentToQuestionInfoSheet(
                        event.category
                    )
                )
            }

            is MainNavigatorEvent.NavigateUp -> {
                navController.navigateUp()
            }
        }
    }
}

sealed class MainNavigatorEvent {
    data object SplashToStart : MainNavigatorEvent()
    data class StartToHome(val noOfQuestions: Int) : MainNavigatorEvent()
    data class HomeToSheet(val category: String) : MainNavigatorEvent()
    data object NavigateUp : MainNavigatorEvent()
}