package com.ds.tapyoutesttask.core.data.repository

import com.ds.tapyoutesttask.core.data.api.PointsApi
import com.ds.tapyoutesttask.core.utils.Operation
import com.ds.tapyoutesttask.di.IoDispatcher
import com.ds.tapyoutesttask.domain.model.Point
import com.ds.tapyoutesttask.domain.repository.PointsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PointsRepositoryImpl @Inject constructor(
    private val pointsApi: PointsApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : PointsRepository {

    override suspend fun getPoints(count: Int): Operation<List<Point>> = withContext(ioDispatcher) {
        try {
            val response = pointsApi.getPoints(count)
            val responseBody = response.body()
            if (response.isSuccessful && responseBody != null && responseBody.points.isNotEmpty())
                return@withContext Operation.Ok(responseBody.points)
            else
                return@withContext Operation.Error(Exception(response.message()))
        } catch (e: Exception) {
            return@withContext Operation.Error(e)
        }
    }
}