package com.ds.tapyoutesttask.feature.start

import android.os.Bundle
import android.view.View
import com.ds.tapyoutesttask.R
import com.ds.tapyoutesttask.core.extension.showToastMessage
import com.ds.tapyoutesttask.core.presentation.BaseFragment
import com.ds.tapyoutesttask.databinding.FragmentStartBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StartFragment : BaseFragment<FragmentStartBinding, StartViewModel>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initListeners()
        observeViewModel()
    }

    private fun initListeners() {
        binding.buttonStart.setOnClickListener {
            val countStr = binding.editTextPoints.text.toString()
            val count = countStr.toIntOrNull()

            if (count == null || count > 50 || count < 2)
                showToastMessage(resources.getString(R.string.start_get_points_validation_error))
            else
                viewModel.getPoints(count)
        }
    }

    private fun observeViewModel() {
        viewModel.isLoading.observe(viewLifecycleOwner) { flag ->
            if (flag) {
                binding.progressBarLoading.visibility = View.VISIBLE
                binding.buttonStart.isEnabled = false
            } else {
                binding.progressBarLoading.visibility = View.INVISIBLE
                binding.buttonStart.isEnabled = true
            }
        }

        viewModel.onPointsUpdate.observe(viewLifecycleOwner) {
            navController.navigate(
                StartFragmentDirections.actionStartFragmentToTableFragment(
                    it.toTypedArray()
                )
            )
        }

        viewModel.onError.observe(viewLifecycleOwner) {
            showToastMessage(resources.getString(R.string.start_get_points_error))
        }
    }
}