package com.example.roombd.basico.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities=[UserEntity::class],version=1)//version para indicar que se han realizado actualizaciones a reflejar en la BD
abstract class UserDatabase: RoomDatabase() {   //la instancia la hará ROOM
    abstract val dao:UserDAO//busca este objeto y hace todo el mapeo
}