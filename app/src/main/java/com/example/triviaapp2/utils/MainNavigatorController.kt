package com.example.triviaapp2.utils

import androidx.navigation.NavController
import com.example.triviaapp2.presentation.splash.SplashFragmentDirections
import com.example.triviaapp2.presentation.start.StartFragmentDirections

class MainNavigatorController(private val navController: NavController) {

    fun setNavigationEvent(event: MainNavigatorEvent){
        when(event){
            is MainNavigatorEvent.SplashToStart -> {
                navController.navigate(SplashFragmentDirections.actionSplashFragmentToStartFragment())
            }

            is MainNavigatorEvent.StartToHome -> {
                navController.navigate(StartFragmentDirections.actionStartFragmentToHomeFragment(event.noOfQuestions))
            }
        }
    }
}

sealed class MainNavigatorEvent {
    data object SplashToStart: MainNavigatorEvent()
    data class StartToHome(val noOfQuestions: Int): MainNavigatorEvent()
}