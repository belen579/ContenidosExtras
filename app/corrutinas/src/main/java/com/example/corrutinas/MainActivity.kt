package com.example.corrutinas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.corrutinas.ui.theme.ContenidosExtrasTheme
import com.example.viewmodel.otromain.LibroViewModel
import com.example.viewmodel.ui.screens.MainScreenLibro

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            ContenidosExtrasTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                }
//            }
//        }
        //5.1.
        val LibroViewModel by viewModels<LibroViewModel>()

            //OPCIÓN CON LISTADO LIBRO
            //5.Uso VIEWMODEL
            MainScreenLibro(LibroViewModel)
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ContenidosExtrasTheme {
        Greeting("Android")
    }
}