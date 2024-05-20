package com.example.notificacioneslocales.home

import android.app.NotificationManager
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.app.NotificationCompat
import androidx.lifecycle.ViewModel
import com.example.notificacioneslocales.MyApp
import com.example.notificacioneslocales.R

class HomeViewModel : ViewModel() {
    var state by mutableStateOf(HomeState())
        private set

    fun sendNotification(context: Context) {
        val notificationManager = context.getSystemService(NotificationManager::class.java)
        val notification = NotificationCompat.Builder(context, MyApp.CHANNEL_ID)//cambiará el channel_ID si tuviésemos en la app distintos tipos notificaciones
            .setContentTitle(state.name)
            .setContentText("Esto es la descripcion")
            .setSmallIcon(R.drawable.logo_notification)//obligatorio,sino, no funciona->drawable/new/vectorAsset/(elegir icono en ClipArt)
            .setAutoCancel(true)//notificación deslizable
            .build()
        notificationManager.notify(state.name.hashCode(), notification)//para que sucesivas notificaciones no se sobreescriban
    }

    fun changeName(text: String) {
        state = state.copy(
            name = text
        )
    }
}
