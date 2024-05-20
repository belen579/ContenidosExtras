package com.example.room.crud.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(product: Product)

    @Query("SELECT * FROM product")//si no autocompleta->problema con la versión ROOM
                                //solución->File/Settings/Editor/Languaje Injections/
                                // crear uno nuevo de Generic Kotlin
                                //en id seleccionar ROOM SQL

    fun getAllProducts(): Flow<List<Product>>//compatibilidad de ROOM con Flow de Kotlin

    @Delete
    suspend fun deleteProduct(product: Product)
}
