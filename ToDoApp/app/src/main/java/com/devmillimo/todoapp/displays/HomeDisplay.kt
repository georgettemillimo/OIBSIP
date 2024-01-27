@file:Suppress("DEPRECATION")

package com.devmillimo.todoapp.displays



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.devmillimo.todoapp.HomeViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.devmillimo.todoapp.R
import com.devmillimo.todoapp.components.ButtonComponent
import com.devmillimo.todoapp.data.TodoEntities


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeDisplay(
    viewModel: HomeViewModel = viewModel()
){
    val todos by viewModel.todos.collectAsState()

    val (dialogOpen, setDialogOpen) = remember {
        mutableStateOf(false)
    }

    if (dialogOpen){
        val (title, setTitle) = remember {
            mutableStateOf("")
        }
        val (subTitle, setSubTitle) = remember {
            mutableStateOf("")
        }

        Dialog(onDismissRequest = { setDialogOpen(false) }) {
            Column(modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.primary)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
                OutlinedTextField(
                    value = title,
                    onValueChange ={
                                   setTitle(it)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label ={
                        Text(text = "Title")
                    }, colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.White,
                        focusedLabelColor = Color.White,
                        focusedTextColor = Color.White
                    ) )

                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = subTitle,
                    onValueChange ={
                                   setSubTitle(it)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label ={
                        Text(text = "Sub Title")
                    }, colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.White,
                        focusedLabelColor = Color.White,
                        focusedTextColor = Color.White
                    ) )

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {

                                 if (title.isNotEmpty() && subTitle.isNotEmpty()){
                                     viewModel.addTodos(TodoEntities(
                                         title = title,
                                         subTitle = subTitle
                                     ))
                                     setDialogOpen(false)
                                 }
                },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
                ) {
                    Text(text = stringResource(id = R.string.submit), color = Color.White)
                }
            }
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.secondary, floatingActionButton =  {
            FloatingActionButton(onClick = {
                                           setDialogOpen(true)
            },
                containerColor = Color.White,
                contentColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Default.Add, contentDescription = null )
            }
        }
    ) {paddings->
        Box(modifier = Modifier
            .fillMaxSize()
            .padding(paddings)){

            LazyColumn(modifier = Modifier.fillMaxSize()){
                items(todos){todo->
                    Text(text = todo.title)
                }
            }
        }
    }
}
