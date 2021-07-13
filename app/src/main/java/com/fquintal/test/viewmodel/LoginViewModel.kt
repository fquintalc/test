package com.fquintal.test.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fquintal.model.Login
import com.fquintal.test.exception.PasswordEmptyLoginException
import com.fquintal.test.exception.UserNameEmptyLoginException
import com.fquintal.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    var login: Login? = null
    var error = MutableLiveData<Exception?>()
    var loggedIn = MutableLiveData(false)
    var logging = MutableLiveData(false)

    init {
        login = Login("", "")
    }

    fun login() {
        if (login!!.username.isEmpty()) {
            error.value = UserNameEmptyLoginException()
            return
        }
        if (login!!.password.isEmpty()) {
            error.value = PasswordEmptyLoginException()
            return
        }
        viewModelScope.launch(Dispatchers.IO) {
            try {
                viewModelScope.launch(Dispatchers.Main) {
                    logging.value = true
                }
                loginUseCase(login!!)
                viewModelScope.launch(Dispatchers.Main) {
                    loggedIn.value = true
                }
            } catch (e: Exception){
                viewModelScope.launch(Dispatchers.Main) {
                    error.value = e
                }

            }
            viewModelScope.launch(Dispatchers.Main) {
                logging.value = false
            }

        }

        error.value = null


    }

}