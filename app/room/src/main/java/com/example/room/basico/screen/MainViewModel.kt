package com.example.roombd.basico.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roombd.basico.data.UserRepository
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository:UserRepository//para tener acceso a lo obtenido desde ROOM
): ViewModel() {
    var state by mutableStateOf(MainState())
        private set

    init{//llamar a recuperar todos los usuarios
        viewModelScope.launch{//uso de corrutina debido a que el método está en método suspend
           state=state.copy(
             users=repository.getUsers()//se recuperan todos los usuarios BD y se pasan al State
           )
        }
    }

    fun onNameChange(name:String){
        state=state.copy(
            name=name
        )
    }

    //funciones para trabajo con ROOM
    fun saveUser(){
        val user=User(
            nombre=state.name
        )
        viewModelScope.launch{//uso de corrutina debido a que insertUser está en método suspend
            repository.insertUser(user)

            state=state.copy(//para que actualice el listado con el nuevo usuario
                users=repository.getUsers()//se recuperan todos los usuarios BD y se pasan al State
            )
        }

    }
}