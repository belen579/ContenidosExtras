package com.example.datastore.ui.state

/* FuncionesUsername.kt */
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.datastore.data.username.UserRepository
import com.example.datastore.username.MyApplication
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class UiState (//define a data class as UiState outside the MyAppViewModel class to manage the UI state.
    val userName: String
)


class MyAppViewModel(
    private val userRepository: UserRepository
): ViewModel() {
    val uiState: StateFlow<UiState> =//using the UiState data class, asynchronously load data from the currentUserName property defined in the UserRepository
        userRepository.currentUserName.map { userName ->
            UiState(userName)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = UiState("Unknown")
        )

    /*
    the purpose of this code is to inject the necessary dependencies (in this case, userRepository)
    from the application context when creating an instance of MyAppViewModel.
    This makes testing and reusing the ViewModel easier and allows for explicit management of dependencies.

In essence, this customization occurs when initializing the ViewModel (MyAppViewModel),
thereby resolving the issue of MyAppViewModel's dependency on UserRepository.
    * */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as MyApplication)
                MyAppViewModel(application.userRepository)
            }
        }
    }

    fun saveUserName(userName: String) {// add a method to save/update the value. This can be implemented in much the same way as defined in the UserRepository class
        viewModelScope.launch {
            userRepository.saveUserName(userName)
        }
    }
}