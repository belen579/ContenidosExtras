package com.example.datastore.username

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.datastore.ui.state.MyAppViewModel

@Composable
fun MainScreenUsername(
    modifier: Modifier = Modifier,

    myAppViewModel: MyAppViewModel = viewModel(factory = MyAppViewModel.Factory)//the Factory defined in MyAppViewModel to the factory parameter, the initialization of this MyAppViewModel will apply the customized initialization
) {

    var userInput by remember {
        mutableStateOf("")
    }

    val savedUserName by myAppViewModel.uiState.collectAsState()

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Hi, ${savedUserName.userName}",
            style = MaterialTheme.typography.displaySmall,
            modifier = Modifier
                .padding(top = 32.dp)
        )
        TextField(
            value = userInput,
            onValueChange = { userInput = it },
            modifier = Modifier
                .padding(vertical = 32.dp)
        )
        Button(
            onClick = { myAppViewModel.saveUserName(userInput)}
        ) {
            Text(text = "SAVE")
        }
    }
}