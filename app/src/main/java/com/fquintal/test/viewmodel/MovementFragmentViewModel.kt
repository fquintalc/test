package com.fquintal.test.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fquintal.model.ListMovement
import com.fquintal.test.remote.DataStorage
import com.fquintal.usecase.GetMovementsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovementFragmentViewModel @Inject constructor(
    private val getMovementsUseCase: GetMovementsUseCase,
    private val dataStorage: DataStorage
) :
    ViewModel() {

    val movements = MutableLiveData(ListMovement(ArrayList()))

    var size = 0

    var synchronizing = MutableLiveData(false)
    var error = MutableLiveData<Exception?>()

    fun loadMovements() {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                viewModelScope.launch(Dispatchers.Main) {
                    synchronizing.value = true
                }
                if (size > 0)
                    size += 1
                getMovementsUseCase(size).let {
                    if (it.data.isNotEmpty()) {
                        viewModelScope.launch(Dispatchers.Main) {
                            movements.value = it
                        }
                    }
                }

                viewModelScope.launch(Dispatchers.Main) {
                    synchronizing.value = false
                }
            } catch (e: Exception) {
                viewModelScope.launch(Dispatchers.Main) {
                    error.value = e
                }
            }
        }

    }

    fun consumeData(): ListMovement {
        val value = movements.value
        movements.value = ListMovement(ArrayList())
        return value!!
    }

    fun getEmail(): String {
        return dataStorage.getLogin()!!.username
    }
}