package com.fquintal.domain.datasource

import com.fquintal.model.Login


interface LoginDataSource {

    fun login(login: Login) : Boolean

    fun dataUser()
}