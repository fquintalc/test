package com.fquintal.usecase

import com.fquintal.domain.repository.LoginRepository
import com.fquintal.model.Login

class LoginUseCase constructor(private val loginRepository: LoginRepository) {

    suspend operator fun invoke(login: Login) {
        val response = loginRepository.login(login)
        if(response){
            loginRepository.getDataUser()
        }
    }
}