package com.ds.tapyoutesttask.feature.start

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ds.tapyoutesttask.core.utils.Operation
import com.ds.tapyoutesttask.core.utils.SingleLiveEvent
import com.ds.tapyoutesttask.domain.model.Point
import com.ds.tapyoutesttask.domain.repository.PointsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    private val pointsRepository: PointsRepository
) : ViewModel() {

    val onPointsUpdate = SingleLiveEvent<List<Point>>()
    val onError = SingleLiveEvent<Any?>()

    fun getPoints(count: Int) {
        viewModelScope.launch {
            when (val pointsOperation = pointsRepository.getPoints(count)) {
                is Operation.Ok -> {
                    onPointsUpdate.value = pointsOperation.data
                }
                is Operation.Error -> {
                    onError.call()
                }
            }
        }
    }
}