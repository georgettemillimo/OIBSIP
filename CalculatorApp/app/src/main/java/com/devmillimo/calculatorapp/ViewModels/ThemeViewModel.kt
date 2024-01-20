package com.devmillimo.calculatorapp.ViewModels


import androidx.compose.runtime.compositionLocalOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


val LocalTheme = compositionLocalOf <ThemeViewModel>{ error("No view Model") }
class ThemeViewModel(val initialDarkMode:Boolean): ViewModel(){


    private val  _darkmode:MutableStateFlow<Boolean> = MutableStateFlow(initialDarkMode)
    val  darkmode = _darkmode.asStateFlow()


    fun toggleTheme(){
        _darkmode.update {!it }
    }
}