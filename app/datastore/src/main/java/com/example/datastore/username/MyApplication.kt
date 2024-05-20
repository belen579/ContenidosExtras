package com.example.datastore.username

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.datastore.data.username.UserRepository

/*
* define dataStore as a Context extension property and delegate it to a specific DataStore instance.
* This allows any part of the application to access DataStore through the same Context,
* ensuring data consistency and improving code reusability.
* */

/* FuncionesUsername.kt */
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "setting"
)

class MyApplication : Application(){//Specify the MyApplication class to inherit from Application()
    lateinit var userRepository: UserRepository
    override fun onCreate() {
        super.onCreate()
        userRepository = UserRepository(dataStore)
    }
}