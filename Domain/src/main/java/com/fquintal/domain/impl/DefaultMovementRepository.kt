package com.fquintal.domain.impl

import com.fquintal.domain.datasource.MovementDataSource
import com.fquintal.model.ListMovement
import com.fquintal.domain.repository.MovementRepository

class DefaultMovementRepository constructor(private val remoteDataSource: MovementDataSource) :
    MovementRepository {
    override fun getMovements(page: Int): ListMovement {
        return remoteDataSource.getMovements(page)
    }
}