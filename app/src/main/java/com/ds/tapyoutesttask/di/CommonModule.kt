package com.ds.tapyoutesttask.di

import com.ds.tapyoutesttask.core.data.repository.PointsRepositoryImpl
import com.ds.tapyoutesttask.domain.repository.PointsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CommonModule {
    @Singleton
    @Binds
    fun bindPointsRepository(pointsRepositoryImpl: PointsRepositoryImpl): PointsRepository
}