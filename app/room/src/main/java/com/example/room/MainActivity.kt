package com.example.room

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.room.crud.data.ProductDatabase
import com.example.room.crud.screen.HomeScreen
import com.example.room.crud.screen.HomeViewModel
import com.example.room.ui.theme.ContenidosExtrasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ContenidosExtrasTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //las BDs generadas con SQLite estarán en la ruta del AVD->/data/user/0/com.example.???/databases()
                    //  /data/user/0/com.example.room/databases/product_db    /user_db

        //APP simple Usuarios BD
                    // llamada a todo lo que necesita ejemplo basico
//        val db= Room.databaseBuilder(this, UserDatabase::class.java,"user_db").build()//llamada a la BD
//        val dao=db.dao//UserDAO para las operaciones BD
//        val repository= UserRepository(dao)//para acceso a Repository y el mapeo de la BD al objeto del UI
//
//        val viewModel = MainViewModel(repository)//se debería usar un Factory en caso de App real con diversos ViewModel

                    //        val viewModel by viewModels<MainViewModel>()

//                    MainScreen(viewModel)

                    //ejemplo CRUD

                    val database =
                        Room.databaseBuilder(this, ProductDatabase::class.java, "product_db")
                            .build()
                    val dao = database.dao
                    val viewModel by viewModels<HomeViewModel>(
                        factoryProducer = {
                        object : ViewModelProvider.Factory {
                            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                                return HomeViewModel(dao) as T
                            }
                        }
                    })
                    HomeScreen(viewModel)
                }
            }
        }
    }
}

