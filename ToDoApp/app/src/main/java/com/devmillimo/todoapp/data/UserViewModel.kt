package com.devmillimo.todoapp.data

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class UserViewModel(private val  dao: UserDao): ViewModel() {


    private val _state = MutableStateFlow(UserState())

    fun onEvent(event: UserEvent){
        when (event){
            UserEvent.HideDialog -> {
                _state.update { it.copy(
                    isRegistering = false
                ) }
            }
            UserEvent.SaveUser -> {
                val firstname = _state.value.firstName
                val secondName = _state.value.secondName
                val email = _state.value.email
                val password = _state.value.password

                if (firstname.isBlank() || secondName.isBlank() || email.isBlank() || password.isBlank()){
                    return
                }
            }
            is UserEvent.SetEmail -> {

                _state.update {
                    it.copy(
                        email = event.email
                    )
                }
            }
            is UserEvent.SetFirstName -> {

                _state.update { it.copy(
                    firstName = event.firstName
                ) }
            }
            is UserEvent.SetPassword -> {

                _state.update { it.copy(
                    password = event.password
                ) }
            }
            is UserEvent.SetSecondName -> {

                _state.update { it.copy(
                    secondName = event.secondName
                ) }
            }
            UserEvent.ShowDialog -> {
                _state.update { it.copy(
                    isRegistering = true
                ) }
            }
        }
    }
}