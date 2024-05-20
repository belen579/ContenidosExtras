package com.example.roombd.basico.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity//para avisar a ROOM que es entidad e la BD
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int?=null,//así habría que pasarlo por parámetro->uso notación ROOM
    val nombre:String
)
