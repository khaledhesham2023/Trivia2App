package com.example.triviaapp2.presentation.parent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.triviaapp2.presentation.loading.LoadingDialog

abstract class ParentFragment<VB : ViewDataBinding> : Fragment() {
    protected lateinit var binding: VB
    private val loading: LoadingDialog by lazy { LoadingDialog(requireContext()) }
    abstract val layout: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,layout,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeComponents()
    }

    abstract fun initializeComponents()
}