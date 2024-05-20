package com.example.roombd.basico.screen



import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MainScreen(viewModel: MainViewModel){
    val state = viewModel.state
    Column(modifier= Modifier.fillMaxWidth()){
        TextField(value=state.name,onValueChange={viewModel.onNameChange(it)})
        Button(onClick={/*TODO*/
            viewModel.saveUser()
        }){
            Text(text="Guardar")
        }

        LazyColumn(modifier=Modifier.fillMaxWidth()){
//            items(listOf<String>()){
            items(items = state.users){
                Text(text=it.nombre)
            }
        }
    }
}

/*ROOM
* Entidades-Objetos a guardar en la BD->data class User
*
* DAO-implementan Queries al estilo SQL para acceder a los datos BD(operaciones CRUD)
*    -Uso interfaz->UserDAO
* BD-bd en donde se guardan los datos
* */