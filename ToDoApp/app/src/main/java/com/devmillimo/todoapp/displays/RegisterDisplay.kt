package com.devmillimo.todoapp.displays

import android.view.Surface
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Surface
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
fun RegisterDisplay(){

    Surface(       modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(28.dp),) {

        Column(modifier = Modifier.fillMaxSize()) {
            CommonText(value = stringResource(R.string.hello))
            HeadingText(value = stringResource(id = R.string.register))

            Spacer(modifier = Modifier.height(30.dp))
            TextFieldComponents(
                labelvalue = stringResource(id = R.string.first_name),
                painterResource(id = R.drawable.user)
            )
            TextFieldComponents(
                labelvalue = stringResource(id = R.string.second_name),
                painterResource(id = R.drawable.user)
            )
            TextFieldComponents(
                labelvalue = stringResource(id = R.string.email),
                painterResource(id = R.drawable.email_)
            )
            PasswordFieldComponent(
                labelvalue = stringResource(id = R.string.password),
                painterResource(id = R.drawable.password_)
            )

            CheckboxComponent(value = stringResource(id = R.string.terms_condition),

                onTextSelected = {
                    //ToDoAppRouter.navigateTo(Display.TermsAndConditionDisplay)
                })

            Spacer(modifier = Modifier.height(30.dp))

            ButtonComponent(value = stringResource(id = R.string.sign_up))

            Spacer(modifier = Modifier.height(10.dp))

            DividerTextComponent()

            ClickableLoginTextComponent(tryingToLogin = true, onTextSelected = {
                ToDoAppRouter.navigateTo(Display.LoginDisplay)
            })

        }

    }




}

@Preview
@Composable
fun PreviewOfRegisterDisplay(){
    RegisterDisplay()
}