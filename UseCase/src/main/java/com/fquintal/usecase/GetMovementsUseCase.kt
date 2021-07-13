package com.fquintal.usecase

import com.fquintal.domain.repository.MovementRepository
import com.fquintal.model.ListMovement


class GetMovementsUseCase constructor(private val movementRepository: MovementRepository) {

    suspend operator fun invoke(page: Int): ListMovement {
        return movementRepository.getMovements(page)
    }
}