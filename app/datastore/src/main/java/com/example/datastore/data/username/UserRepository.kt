package com.example.datastore.data.username

/* UserRepository.kt */
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/*
In DataStore (Preferences), data is managed by key & value, and the value we’re dealing with here is a username,
 which is a string. So, we use stringPreferencesKey()
* */

class UserRepository(private val dataStore: DataStore<Preferences>) {
    private companion object {
        /*
        you can manage each Preferences key as a static object (singleton) that only exists once in the class.
        Managing just a username is easy, but for cases where you need to manage many keys,
        such as toggling other settings on and off, managing them with a companion object becomes even more advantageous.
        * */
        val USER_NAME = stringPreferencesKey("user_name")
    }

    val currentUserName: Flow<String> =
        dataStore.data.map { preferences ->
            preferences[USER_NAME] ?: "Unknown"
            /*
            Next, we define a property to read the string type value stored on the device.
            This can be achieved by expanding the data of the dataStore received in the constructor with map(),
            and specifying the key defined earlier in the companion object.
            * */
        }

    suspend fun saveUserName(userName: String) {// the saveUserName() method defined in the UserRepository class is a suspend fun, it needs to be called within a coroutine scope.
        dataStore.edit { preferences ->
            preferences[USER_NAME] = userName
            /*
            Now, since we can only read data with this setup, let’s add a method to save or update the data.
            Add a method to save the string received as an argument using edit().
            * */
        }
    }
}