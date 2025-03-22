package com.ds.tapyoutesttask.domain.repository

import com.ds.tapyoutesttask.core.utils.Operation
import com.ds.tapyoutesttask.domain.model.Point

interface PointsRepository {
    suspend fun getPoints(count: Int) : Operation<List<Point>>
}