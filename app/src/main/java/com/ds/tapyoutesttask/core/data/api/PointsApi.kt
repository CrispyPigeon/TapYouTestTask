package com.ds.tapyoutesttask.core.data.api

import com.ds.tapyoutesttask.core.data.api.model.PointsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PointsApi {
    @GET("api/test/points")
    suspend fun getPoints(
        @Query("count") count: Int,
    ): Response<PointsResponse>
}