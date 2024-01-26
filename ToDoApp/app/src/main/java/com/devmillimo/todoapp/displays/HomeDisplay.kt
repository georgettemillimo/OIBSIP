@file:Suppress("DEPRECATION")

package com.devmillimo.todoapp.displays



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.devmillimo.todoapp.HomeViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


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
        Dialog(onDismissRequest = { setDialogOpen(false) }) {
            Column(modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.primary)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center) {
                OutlinedTextField(
                    value = "",
                    onValueChange ={},
                    modifier = Modifier.fillMaxWidth(),
                    label ={
                        Text(text = "Title")
                    }, colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedLabelColor = Color.White,
                        unfocusedLabelColor = Color.White,

                    ) )
            }
        }
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.secondary, floatingActionButton =  {
            FloatingActionButton(onClick = { /*TODO*/ },
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


        }


    }


}