package com.devmillimo.todoapp.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.devmillimo.todoapp.screen.*



@Composable
fun ToDoApp (){

        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color.White
        ) {

            Crossfade(targetState = ToDoAppRouter.currentDisplay) {currentState->
                when(currentState.value){

                    is  Display.RegisterDisplay->{
                        RegisterDisplay()
                    }
                    is Display.TermsAndConditionDisplay->{
                        TernmsAndConditionDisplay()
                    }
                    is Display.LoginDisplay->{
                        LoginDisplay()
                    }
                    is Display.HomeDisplay->{
                        HomeDisplay()
                    }
                }

            }

        }
    }
