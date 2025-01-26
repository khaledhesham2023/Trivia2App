package com.example.triviaapp2.utils

import androidx.navigation.NavController
import com.example.triviaapp2.presentation.splash.SplashFragmentDirections
import com.example.triviaapp2.presentation.start.StartFragmentDirections

class MainNavigatorController(val navController: NavController) {

    fun setNavigationEvent(event: MainNavigatorEvent){
        when(event){
            MainNavigatorEvent.SplashToStart -> {
                navController.navigate(SplashFragmentDirections.actionSplashFragmentToStartFragment())
            }

            MainNavigatorEvent.StartToHome -> {
                navController.navigate(StartFragmentDirections.actionStartFragmentToHomeFragment())
            }
        }
    }
}

enum class MainNavigatorEvent {
    SplashToStart,
    StartToHome,
}