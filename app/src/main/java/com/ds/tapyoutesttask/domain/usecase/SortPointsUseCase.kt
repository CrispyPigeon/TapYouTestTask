package com.ds.tapyoutesttask.domain.usecase

import com.ds.tapyoutesttask.domain.model.Point
import javax.inject.Inject

class SortPointsUseCase @Inject constructor() {
    fun execute(points: List<Point>): List<Point> {
        return points.sortedBy { it.x }
    }
}