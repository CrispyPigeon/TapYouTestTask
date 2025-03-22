package com.ds.tapyoutesttask.feature.start

import android.os.Bundle
import android.view.View
import com.ds.tapyoutesttask.core.presentation.BaseFragment
import com.ds.tapyoutesttask.databinding.FragmentStartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartFragment : BaseFragment<FragmentStartBinding, StartViewModel>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
    }

    private fun initListeners() {
        binding.buttonStart.setOnClickListener {
            navController.navigate(StartFragmentDirections.actionStartFragmentToTableFragment())
        }
    }
}