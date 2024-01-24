package com.devmillimo.todoapp.app

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

sealed class Display {
    object RegisterDisplay : Display()
    object TermsAndConditionDisplay : Display()

    object LoginDisplay: Display()
}
object ToDoAppRouter{
    var currentDisplay: MutableState<Display> = mutableStateOf(Display.RegisterDisplay)

    fun navigateTo(destination: Display){
        currentDisplay.value = destination
    }
}