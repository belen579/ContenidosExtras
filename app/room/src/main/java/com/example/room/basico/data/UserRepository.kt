package com.example.roombd.basico.data

import com.example.roombd.basico.screen.User

class UserRepository (private val userDAO: UserDAO)
{//para la transcripción de User, UserEntity y UserDAo
    suspend fun getUsers():List<User>{
        val entities=userDAO.getUsers()
        return entities.map{//conversión al tipo requerido
            User(nombre=it.nombre)
        }
    }

    suspend fun insertUser(user: User){
        val entity = UserEntity(nombre=user.nombre)//hacer que sea id null por defecto
        userDAO.insertUsers(entity)
    }

}