package com.fquintal.test.hilt

import com.fquintal.domain.datasource.MovementDataSource
import com.fquintal.domain.impl.DefaultMovementRepository
import com.fquintal.domain.repository.MovementRepository
import com.fquintal.usecase.GetMovementsUseCase
import com.fquintal.test.remote.DataStorage
import com.fquintal.test.remote.datasources.RemoteMovementDataSource
import com.fquintal.test.remote.service.FinerioService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MovementModule {

    private fun getRemoteMovementDataSource(
        finerioService: FinerioService,
        dataStorage: DataStorage
    ): MovementDataSource {
        return RemoteMovementDataSource(finerioService, dataStorage)
    }

    @Provides
    @Singleton
    fun getMovementRepository(
        finerioService: FinerioService,
        dataStorage: DataStorage
    ): MovementRepository {
        return DefaultMovementRepository(getRemoteMovementDataSource(finerioService, dataStorage))
    }

    @Provides
    @Singleton
    fun getGetMovementUseCase(movementRepository: MovementRepository): GetMovementsUseCase {
        return GetMovementsUseCase(movementRepository)
    }

}