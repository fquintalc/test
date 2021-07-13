package com.fquintal.test.remote.datasources

import com.fquintal.domain.datasource.LoginDataSource
import com.fquintal.model.Login
import com.fquintal.test.remote.DataStorage
import com.fquintal.test.remote.service.FinerioService
import com.fquintal.test.exception.UsernameAndPasswordWrongException

class RemoteLoginDataSource constructor(
    private val finerioService: FinerioService,
    private val dataStorage: DataStorage
) : LoginDataSource {
    override fun login(login: Login): Boolean {
        val response = finerioService.login(login).execute()
        if(response.isSuccessful){
            dataStorage.saveLogin(response.body()!!)
        }
        else{
            if(response.code() == 401){
                throw UsernameAndPasswordWrongException()
            }
        }
        return response.isSuccessful
    }

    override fun dataUser() {
        val login = dataStorage.getLogin()!!
        val authorization = login.tokenType + " " + login.accessToken
        val response = finerioService.getDataUser(authorization).execute()
        if(response.isSuccessful){
            dataStorage.saveDataUser(response.body()!!)
        }
    }
}