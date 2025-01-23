package com.example.triviaapp2.utils

import androidx.navigation.NavController
import com.example.triviaapp2.presentation.splash.SplashFragmentDirections

class MainNavigatorController(val navController: NavController) {

    fun setNavigationEvent(event: MainNavigatorEvent){
        when(event){
            MainNavigatorEvent.SplashToMain -> {
                navController.navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
            }
        }
    }
}

enum class MainNavigatorEvent {
    SplashToMain
}