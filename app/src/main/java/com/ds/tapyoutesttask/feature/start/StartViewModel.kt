package com.ds.tapyoutesttask.feature.start

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ds.tapyoutesttask.core.utils.Operation
import com.ds.tapyoutesttask.core.utils.SingleLiveEvent
import com.ds.tapyoutesttask.domain.model.Point
import com.ds.tapyoutesttask.domain.repository.PointsRepository
import com.ds.tapyoutesttask.domain.usecase.SortPointsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    private val pointsRepository: PointsRepository,
    private val sortPointsUseCase: SortPointsUseCase,
) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean>
        get() = _isLoading

    val onPointsUpdate = SingleLiveEvent<List<Point>>()
    val onError = SingleLiveEvent<Any?>()

    fun getPoints(count: Int) {
        viewModelScope.launch {
            _isLoading.value = true

            when (val pointsOperation = pointsRepository.getPoints(count)) {
                is Operation.Ok -> {
                    onPointsUpdate.value = sortPointsUseCase.execute(
                        pointsOperation.data
                    )
                }

                is Operation.Error -> {
                    onError.call()
                }
            }

            _isLoading.value = false
        }
    }
}