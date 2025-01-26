package com.example.triviaapp2.presentation.start

import androidx.navigation.fragment.findNavController
import com.example.triviaapp2.R
import com.example.triviaapp2.databinding.FragmentStartBinding
import com.example.triviaapp2.presentation.parent.ParentFragment
import com.example.triviaapp2.utils.MainNavigatorController
import com.example.triviaapp2.utils.MainNavigatorEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartFragment : ParentFragment<FragmentStartBinding>() {
    override val layout: Int
        get() = R.layout.fragment_start

    private val mNavigator: MainNavigatorController by lazy {
        MainNavigatorController(
            findNavController()
        )
    }

    override fun initializeComponents() {
        setupClicks()
    }

    private fun isNumberOk(): Boolean {
        var isNumberOk = true
        if (binding.appCompatEditText.text.toString() == "") {
            isNumberOk = false
        } else if (binding.appCompatEditText.text.toString().toInt() < 1 || binding.appCompatEditText.text.toString().toInt() > 50) {
            isNumberOk = false
        }
        return isNumberOk
    }

    private fun setupClicks() {
        binding.startBtn.setOnClickListener {
            if (isNumberOk()) {
                mNavigator.setNavigationEvent(MainNavigatorEvent.StartToHome)
            } else {
                binding.appCompatEditText.error = getString(R.string.no_of_questions_error)
            }
        }
    }
}