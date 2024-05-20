package com.example.notificacioneslocales.home

import android.Manifest
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@Composable
fun HomeScreen(
    viewModel: HomeViewModel
) {
    val state = viewModel.state
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        NotificationScreen()

        TextField(value = state.name, onValueChange = { viewModel.changeName(it) })
        Button(onClick = {
            Toast.makeText(context,"Llegó",Toast.LENGTH_LONG).show()
            viewModel.sendNotification(context)
        }) {
            Text(text = "Enviar Notificacion")
        }
    }
}


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun NotificationScreen(){
    val permissionState =
        rememberPermissionState(permission = Manifest.permission.POST_NOTIFICATIONS)//para más de una->..Multiple..
    LaunchedEffect(true){
        permissionState.launchPermissionRequest()
    }

    if(permissionState.status.isGranted){
        Text(text="Permiso Concedido")
    }else {
        Text(text = "Permiso Denegado")
    }
}