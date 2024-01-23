package com.devmillimo.todoapp.navigation

import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.compose.runtime.staticCompositionLocalOf

private val  LocalBackPressedDispatcher =
    staticCompositionLocalOf<OnBackPressedDispatcherOwner?>{ null }

private class ComposableBacknavigationHandler(enabled: Boolean) : OnBackPressedCallback(enabled){
    lateinit var onBackPressed: () -> Unit

    override fun handleOnbackPressed() {
        onBackPressed
    }
}