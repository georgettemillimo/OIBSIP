package com.devmillimo.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.devmillimo.todoapp.app.ToDoApp
import com.devmillimo.todoapp.displays.HomeDisplay
import com.devmillimo.todoapp.displays.LoginDisplay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           ToDoApp()
        }
    }
}
