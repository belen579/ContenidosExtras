package com.example.room.crud.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val name: String,
    val price: Double
)
