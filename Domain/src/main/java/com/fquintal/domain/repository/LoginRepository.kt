package com.fquintal.domain.repository

import com.fquintal.model.Login


interface LoginRepository {

    fun login(login: Login) : Boolean

    fun getDataUser()
}