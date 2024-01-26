package com.devmillimo.todoapp.app

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.devmillimo.todoapp.displays.*
import com.devmillimo.todoapp.ui.theme.ToDoAppTheme


@Composable
fun ToDoApp (){

    ToDoAppTheme {
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


}