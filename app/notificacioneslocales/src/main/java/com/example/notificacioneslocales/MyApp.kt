package com.example.notificacioneslocales

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class MyApp : Application() {
    companion object {
        const val CHANNEL_ID = "my_channelasdasd"
    }

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {//hará un channel para cada tipo de notificación con la que se quiera trabajar
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//necesita minsdk de 24 en adelante
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Actualizaciones de producto",
                NotificationManager.IMPORTANCE_HIGH
            )

            channel.description = "Este canal se usa para notificar actualizaciones del producto"
            val notificationManager = this.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }
}
