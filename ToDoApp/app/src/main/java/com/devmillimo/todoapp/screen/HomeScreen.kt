@file:Suppress("DEPRECATION")

package com.devmillimo.todoapp.screen


import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.devmillimo.todoapp.HomeViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.devmillimo.todoapp.R
import com.devmillimo.todoapp.data.TodoEntities
import com.devmillimo.todoapp.data.addDate


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeDisplay(
    viewModel: HomeViewModel = viewModel(),
) {
    val todos by viewModel.todos.collectAsState()

    val (dialogOpen, setDialogOpen) = remember {
        mutableStateOf(false)
    }

    if (dialogOpen) {
        val (title, setTitle) = remember {
            mutableStateOf("")
        }
        val (subTitle, setSubTitle) = remember {
            mutableStateOf("")
        }

        Dialog(onDismissRequest = { setDialogOpen(false) }) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color.LightGray)
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    value = title,
                    onValueChange = {
                        setTitle(it)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = "Title")
                    }, colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.White,
                        focusedLabelColor = Color.White,
                        focusedTextColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(6.dp))
                OutlinedTextField(
                    value = subTitle,
                    onValueChange = {
                        setSubTitle(it)
                    },
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = "Sub Title")
                    }, colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.White,
                        focusedLabelColor = Color.White,
                        focusedTextColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {

                        if (title.isNotEmpty() && subTitle.isNotEmpty()) {
                            viewModel.addTodos(
                                TodoEntities(
                                    title = title,
                                    subTitle = subTitle
                                )
                            )
                            setDialogOpen(false)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = Brush.horizontalGradient(
                                listOf(
                                    Color.LightGray,
                                   Color.Magenta,

                                )
                            ),
                            shape = RoundedCornerShape(50.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(Color.Transparent)
                ) {
                    Text(text = stringResource(id = R.string.submit), color = Color.White)
                }
            }
        }
    }

    Scaffold(
        containerColor = Color.LightGray, floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    setDialogOpen(true)
                },
                containerColor = Color.Magenta,
                contentColor = Color.White
            ) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    ) { paddings ->
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            AnimatedVisibility(
                visible = todos.isEmpty(),
                enter = scaleIn() + fadeIn(),
                exit = scaleOut() + fadeOut(),
            ) {
                Text(
                    text = stringResource(id = R.string.Notodos),
                    color = Color.White,
                    fontSize = 22.sp
                )
            }

            AnimatedVisibility(
                visible = todos.isNotEmpty(),
                enter = scaleIn() + fadeIn(),
                exit = scaleOut() + fadeOut(),
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            bottom = paddings.calculateBottomPadding() + 8.dp,
                            top = 8.dp,
                            end = 8.dp,
                            start = 8.dp
                        ), verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(todos.sortedBy { it.done }, key = { it.id })
                    { todo ->
                        TodoItem(todo = todo, onClick = {
                            viewModel.updateTodos(
                                todo.copy(done = !todo.done)
                            )
                        }, onDelete = {
                            viewModel.deleteTodos(todo)
                        })
                    }
                }
            }

        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyItemScope.TodoItem(todo: TodoEntities, onClick: () -> Unit, onDelete: () -> Unit) {
    val color by animateColorAsState(
        targetValue = if (todo.done) Color(0xff24d65f) else Color(0xffff6363),
        animationSpec = tween(500)
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .animateItemPlacement(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            ),
        contentAlignment = Alignment.BottomEnd
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(5.dp))
                .background(color)
                .clickable {
                    onClick
                }
                .padding(
                    horizontal = 8.dp,
                    vertical = 16.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(25.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .padding(4.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row {
                        AnimatedVisibility(
                            visible = todo.done,
                            enter = scaleIn() + fadeIn(),
                            exit = scaleOut() + fadeOut()
                        ) {
                            Icon(Icons.Default.Check, contentDescription = null, tint = color)
                        }
                    }
                    Row {
                        AnimatedVisibility(
                            visible = !todo.done,
                            enter = scaleIn() + fadeIn(),
                            exit = scaleOut() + fadeOut()
                        ) {
                            Icon(Icons.Default.Close, contentDescription = null, tint = color)
                        }
                    }

                }

                Column {
                    Text(
                        text = todo.title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        color = Color.White
                    )
                    Text(
                        text = todo.subTitle,
                        fontSize = 12.sp,
                        color = Color(0xffebebeb)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .size(25.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(4.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.Delete,
                    tint = Color.White,
                    contentDescription = null,
                    modifier = Modifier.clickable {
                        onDelete()
                    })
            }

        }

        Text(
            text = todo.addDate,
            modifier = Modifier.padding(4.dp),
            Color(0xffebebebeb),
            fontSize = 10.sp
        )


    }

}

