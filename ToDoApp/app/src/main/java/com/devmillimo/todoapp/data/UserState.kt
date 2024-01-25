package com.devmillimo.todoapp.data

data class UserState(
    val users: List<Users> = emptyList(),
    val firstName: String ="",
    val secondName: String ="",
    val email: String ="",
    val password: String ="",
    val isRegistering: Boolean = false
)
