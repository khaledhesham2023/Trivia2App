package com.example.triviaapp2.presentation

import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.triviaapp2.R
import com.example.triviaapp2.databinding.ActivityMainBinding
import com.example.triviaapp2.presentation.parent.ParentActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ParentActivity<ActivityMainBinding>() {

    private lateinit var navController: NavController

    override val layout: Int
        get() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController =
            (supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment).findNavController()

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}