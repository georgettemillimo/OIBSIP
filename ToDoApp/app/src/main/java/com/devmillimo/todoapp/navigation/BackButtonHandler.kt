package com.devmillimo.todoapp.navigation

import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcherOwner
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalLifecycleOwner

private val  LocalBackPressedDispatcher =
    staticCompositionLocalOf<OnBackPressedDispatcherOwner?>{ null }
/*
private class ComposableBacknavigationHandler(enabled: Boolean) : OnBackPressedCallback(enabled){
    lateinit var onBackPressed: () -> Unit

    override fun handleOnbackPressed() {
        onBackPressed()
    }
}

@Composable
internal  fun SystemBackButtonHandler(onBackPressed: () -> Unit){

    CompositionLocalProvider(values = LocalBackPressedDispatcher provides LocalLifecycleOwner.current as) {
        ComponentActivity.
    }
}
*/