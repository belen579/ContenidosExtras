package com.example.apirest.crud.home

import com.squareup.moshi.Json//es el conversor de lo que viene de APIREST con JSON
//OJO, debería haber también ProductDTO
data class Product(
    @field:Json(name = "_id")//pq desde la web con APIRest, devuelve esto _id en el JSON
    val id: String,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "price")
    val price: Double
)

data class ProductDto(
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "price")
    val price: Double
)

