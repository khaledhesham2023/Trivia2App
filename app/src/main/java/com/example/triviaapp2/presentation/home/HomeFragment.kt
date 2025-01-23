package com.example.triviaapp2.presentation.home

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.triviaapp2.R
import com.example.triviaapp2.databinding.FragmentHomeBinding
import com.example.triviaapp2.presentation.loading.LoadingDialog
import com.example.triviaapp2.presentation.parent.ParentFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : ParentFragment<FragmentHomeBinding>() {
    override val layout: Int
        get() = R.layout.fragment_home

    override fun initializeComponents() {
    }
}