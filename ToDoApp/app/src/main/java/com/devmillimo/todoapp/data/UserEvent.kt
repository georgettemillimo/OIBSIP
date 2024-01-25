package com.devmillimo.todoapp.data

sealed interface UserEvent{
    object SaveUser: UserEvent
    data class SetFirstName(val firstName: String): UserEvent
    data class SetSecondName(val secondName: String): UserEvent
    data class SetEmail(val email: String): UserEvent
    data class SetPassword(val password: String): UserEvent
    object ShowDialog: UserEvent
    object HideDialog: UserEvent


}