package com.devmillimo.todoapp.displays

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devmillimo.todoapp.R
import com.devmillimo.todoapp.components.CommonText
import com.devmillimo.todoapp.components.HeadingText

@Composable
fun RegisterDisplay(){
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp),
    ) {
        
        Column(modifier = Modifier.fillMaxSize()) {
            CommonText(value = stringResource(R.string.hello))
            HeadingText(value = stringResource(id = R.string.register))
        }

    }
}

@Preview
@Composable
fun PreviewOfRegisterDisplay(){
    RegisterDisplay()
}