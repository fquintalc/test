package com.fquintal.domain.datasource

import com.fquintal.model.ListMovement


interface MovementDataSource {

    fun getMovements(page: Int): ListMovement
}