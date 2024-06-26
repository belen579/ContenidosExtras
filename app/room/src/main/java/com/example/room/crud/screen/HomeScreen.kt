package com.example.room.crud.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

//sacado del link https://www.youtube.com/watch?v=VHmByx_Xdys&list=PLFV6hE_GSkxW9T1nHvJFZX0R9Ca04eFm7&index=12

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {
    val state = viewModel.state

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(text = "Mis Productos", fontSize = 20.sp, fontWeight = FontWeight.SemiBold)
        TextField(
            value = state.productName,
            onValueChange = { viewModel.changeName(it) },
            placeholder = { Text(text = "Nombre del producto") }
        )
        TextField(
            value = state.productPrice,
            onValueChange = { viewModel.changePrice(it) },
            placeholder = { Text(text = "Precio") }
        )
        Button(onClick = { viewModel.createProduct() }) {
            Text(text = "Agregar Producto")
        }
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(state.products) {
                ProductItem(product = it, modifier = Modifier.fillMaxWidth(), onEdit = {
                    viewModel.editProduct(it)
                }, onDelete = {
                        viewModel.deleteProduct(it)
                    })
            }
        }
    }
}
