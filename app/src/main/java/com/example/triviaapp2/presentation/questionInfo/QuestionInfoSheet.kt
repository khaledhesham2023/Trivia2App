package com.example.triviaapp2.presentation.questionInfo

import android.animation.ObjectAnimator
import android.media.MediaPlayer
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.animation.DecelerateInterpolator
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.triviaapp2.R
import com.example.triviaapp2.databinding.DialogNextQuestionBinding
import com.example.triviaapp2.presentation.parent.ParentSheetFragment
import com.example.triviaapp2.utils.MainNavigatorController
import com.example.triviaapp2.utils.MainNavigatorEvent
import com.example.triviaapp2.utils.enums.Categories

class QuestionInfoSheet : ParentSheetFragment<DialogNextQuestionBinding>() {
    override val isStyle: Boolean
        get() = true
    override val isCancel: Boolean
        get() = false
    override val isFull: Boolean
        get() = true
    private val args: QuestionInfoSheetArgs by navArgs()
    private val navigator by lazy { MainNavigatorController(findNavController()) }
    private lateinit var prepareMediaPlayer: MediaPlayer
    private lateinit var categoryMedia: MediaPlayer

    override fun initializeComponents(savedInstanceState: Bundle?) {
        setupClicks()
        prepareMediaPlayer = MediaPlayer.create(requireContext(), R.raw.prepare)
        val category = args.category
        val icon = configureIcon(category)
        categoryMedia = configureCategoryMedia(category)
        prepareMediaPlayer.apply {
            start()
            setOnCompletionListener {
                stop()
                categoryMedia.start()
            }
        }
        binding.category.text = Html.fromHtml(category)
        binding.icon.setImageResource(icon)
        val swivelAnimation =
            ObjectAnimator.ofFloat(binding.icon, "rotationY", 0f, 360f).apply {
                duration = 2000L
                interpolator = DecelerateInterpolator()
            }
        swivelAnimation.start()
    }

    private fun configureCategoryMedia(category: String): MediaPlayer {
        return MediaPlayer.create(requireContext(),Categories.entries.find { categoryItem -> categoryItem.value == category }?.voice ?: R.raw.general_knowledge)
    }

    private fun setupClicks() {
        binding.answerBtn.setOnClickListener {
            navigator.setNavigationEvent(MainNavigatorEvent.NavigateUp)
        }
    }

    private fun configureIcon(category: String): Int {
        return Categories.entries.find { categoryItem -> categoryItem.value == category }?.iconId
            ?: R.drawable.general_knowledge
    }

    override val bindingInflater: (LayoutInflater) -> DialogNextQuestionBinding
        get() = DialogNextQuestionBinding::inflate
}