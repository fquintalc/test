package com.fquintal.domain.repository

import com.fquintal.model.ListMovement


interface MovementRepository {

    fun getMovements(page : Int) : ListMovement
}