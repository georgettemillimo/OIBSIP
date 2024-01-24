package com.devmillimo.todoapp.displays

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devmillimo.todoapp.R
import com.devmillimo.todoapp.app.Display
import com.devmillimo.todoapp.app.ToDoAppRouter
import com.devmillimo.todoapp.components.*

@Composable
fun LoginDisplay(){
    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)
    ){
       Column {
           CommonText(value = stringResource(id = R.string.login))
           HeadingText(value = stringResource(id = R.string.welcome))

           Spacer(modifier = Modifier.height(30.dp))

           TextFieldComponents(labelvalue = stringResource(id = R.string.email),
               painterResource(R.drawable.email_) )

           PasswordFieldComponent(labelvalue = stringResource(id = R.string.password),
               painterResource(R.drawable.password_))

           Spacer(modifier = Modifier.height(30.dp))
           UnderLinedCommonText(value = stringResource(id = R.string.forgot_password))

           ButtonComponent(value = stringResource(id = R.string.login))

           Spacer(modifier = Modifier.height(30.dp))
           DividerTextComponent()

           ClickableLoginTextComponent(tryingToLogin = false, onTextSelected = {
                ToDoAppRouter.navigateTo(Display.RegisterDisplay)
           })

       }
    }

}

@Preview
@Composable

fun LoginDisplayPreview(){
    LoginDisplay()
}
