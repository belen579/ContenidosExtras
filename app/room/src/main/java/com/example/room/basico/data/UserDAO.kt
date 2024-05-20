package com.example.roombd.basico.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)//en caso de que haya duplicado, no hay q hacer update
    suspend fun insertUsers(user:UserEntity)

    @Query("SELECT * FROM UserEntity" )
    suspend fun getUsers():List<UserEntity>
}