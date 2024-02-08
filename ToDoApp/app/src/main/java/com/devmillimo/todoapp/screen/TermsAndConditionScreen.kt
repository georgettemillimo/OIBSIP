package com.devmillimo.todoapp.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devmillimo.todoapp.R
import com.devmillimo.todoapp.components.CommonTextsFiles
import com.devmillimo.todoapp.components.HeadingText


@Composable
fun TernmsAndConditionDisplay() {

    Surface(modifier = Modifier
        .fillMaxHeight()
        .fillMaxHeight()
        .heightIn(70.dp)
        .background(color = Color.White)
        .padding(16.dp)) {

    }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)) {
        HeadingText(value = stringResource(id = R.string.terms_condition_header))
        CommonTextsFiles(value = stringResource(id = R.string.terms_condition))
    }

   /* SystemBackButtonHandler {
        ToDoAppRouter.navigateTo(Display.RegisterDisplay)
    }*/
}

@Composable
@Preview

fun TernmsAndConditionDisplayPriview(){
    TernmsAndConditionDisplay()
}