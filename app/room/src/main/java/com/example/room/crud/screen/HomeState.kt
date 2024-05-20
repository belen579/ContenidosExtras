package com.example.room.crud.screen

import com.example.room.crud.data.Product

data class HomeState(
    val products: List<Product> = emptyList(),

//    val products: List<Product> = listOf(//FakeList
//        Product("","Producto 1",34.5,),
//        Product("","Producto 2",54.98,),
//    ),

    val productName: String = "",
    val productPrice: String = "",
    val productId: String? = null
)
