package com.example.roombd.basico.screen

data class MainState(
    val name:String = "",
    val users:List<User> = emptyList()
)
