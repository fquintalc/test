package com.fquintal.domain.impl

import com.fquintal.domain.datasource.LoginDataSource
import com.fquintal.model.Login
import com.fquintal.domain.repository.LoginRepository

class DefaultLoginRepository constructor(private val remoteDataSource: LoginDataSource) :
    LoginRepository {
    override fun login(login: Login): Boolean {
        return remoteDataSource.login(login)
    }

    override fun getDataUser() {
        remoteDataSource.dataUser()
    }
}