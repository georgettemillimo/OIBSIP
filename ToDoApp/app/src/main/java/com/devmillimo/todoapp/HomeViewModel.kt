package com.devmillimo.todoapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devmillimo.todoapp.data.TodoEntities
import com.devmillimo.todoapp.repositories.TodoRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeViewModel: ViewModel(), KoinComponent {
    private val repo: TodoRepo by inject()


    private val _todos: MutableStateFlow<List<TodoEntities>> = MutableStateFlow(emptyList())
    val todos = _todos.asStateFlow()

    init {
        getTodos()
    }

    private fun getTodos(){
        viewModelScope.launch(Dispatchers.IO) {
            repo.getTodos().collect(){data->
                _todos.update { data }
            }
        }
    }

    fun updateTodos(todo: TodoEntities){
        viewModelScope.launch(Dispatchers.IO) {
            repo.updateTodo(todo)
        }
    }
    fun deleteTodos(todo: TodoEntities){
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteTodo(todo)
        }
    }
    fun addTodos(todo: TodoEntities){
        viewModelScope.launch(Dispatchers.IO) {
            repo.addTodo(todo)
        }
    }
}