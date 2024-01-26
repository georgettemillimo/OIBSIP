package com.devmillimo.todoapp.repositories

import com.devmillimo.todoapp.data.TodoEntities
import kotlinx.coroutines.flow.Flow

interface TodoRepo {
    suspend fun getTodos(): Flow<List<TodoEntities>>
    suspend fun addTodo(todo: TodoEntities)
    suspend fun updateTodo(todo: TodoEntities)
    suspend fun deleteTodo(todo: TodoEntities)
}