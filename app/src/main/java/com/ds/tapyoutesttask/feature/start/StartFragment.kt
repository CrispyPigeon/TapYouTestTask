package com.ds.tapyoutesttask.feature.start

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.ds.tapyoutesttask.R
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
            viewModel.getPoints(countStr.toInt())
        }
    }

    private fun observeViewModel() {
        viewModel.onPointsUpdate.observe(viewLifecycleOwner) {
            navController.navigate(StartFragmentDirections.actionStartFragmentToTableFragment())
        }

        viewModel.onError.observe(viewLifecycleOwner) {
            Toast.makeText(
                requireContext(),
                resources.getString(R.string.start_get_points_error),
                Toast.LENGTH_LONG
            ).show()
        }
    }
}