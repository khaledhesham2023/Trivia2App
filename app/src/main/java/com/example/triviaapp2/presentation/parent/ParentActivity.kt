package com.example.triviaapp2.presentation.parent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class ParentActivity<VB: ViewDataBinding> : AppCompatActivity() {
    protected lateinit var binding: VB
    abstract val layout : Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,layout)
    }
}