package com.example.apirest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.apirest.crud.home.HomeScreen
import com.example.apirest.crud.home.HomeViewModel
import com.example.apirest.crud.home.ProductService
import com.example.apirest.ui.theme.ContenidosExtrasTheme

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
//                    Greeting("Android")
//                    MarsPhotosApp()

                    //otra app listaCompra
                    //se debería usar el Factory como en ROOM
                    val viewModel = HomeViewModel(ProductService.instance)
                    HomeScreen(viewModel)
                }
            }
        }
    }
}

//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    ContenidosExtrasTheme {
//        Greeting("Android")
//    }
//}