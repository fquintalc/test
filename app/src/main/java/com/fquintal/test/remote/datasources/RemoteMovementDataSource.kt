package com.fquintal.test.remote.datasources

import com.fquintal.domain.datasource.MovementDataSource
import com.fquintal.model.ListMovement
import com.fquintal.test.remote.DataStorage
import com.fquintal.test.remote.service.FinerioService

class RemoteMovementDataSource constructor(
    private val finerioService: FinerioService,
    private val dataStorage: DataStorage
) : MovementDataSource {
    override fun getMovements(page: Int): ListMovement {
        val login = dataStorage.getLogin()!!
        val authorization = login.tokenType + " " + login.accessToken
        val dataUser = dataStorage.getDataUser()!!
        val response = finerioService.getMovements(
            authorization, dataUser.id, true, page, 10,
            includeCharges = true,
            includeDuplicates = true
        ).execute()
        return response.body()!!
    }
}