package com.ds.tapyoutesttask.feature.table

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.navArgs
import com.ds.tapyoutesttask.core.presentation.BaseFragment
import com.ds.tapyoutesttask.databinding.FragmentTableBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TableFragment : BaseFragment<FragmentTableBinding, TableViewModel>() {

    private val args: TableFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        val pointsList = args.points.toList()

        with(binding) {
            recyclerPoints.adapter = PointsAdapter(pointsList)
            graphView.setPoints(pointsList)
        }
    }
}