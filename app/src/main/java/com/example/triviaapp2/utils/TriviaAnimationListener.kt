package com.example.triviaapp2.utils

import android.animation.Animator
import android.animation.Animator.AnimatorListener

open class TriviaAnimationListener : AnimatorListener {

    override fun onAnimationStart(animation: Animator) {
        // no-op
    }

    override fun onAnimationEnd(animation: Animator) {
        // no-op
    }

    override fun onAnimationCancel(animation: Animator) {
        // no-op
    }

    override fun onAnimationRepeat(animation: Animator) {}
}