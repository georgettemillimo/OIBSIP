package com.devmillimo.todoapp.repositories

import com.devmillimo.todoapp.data.TodoDatabase
import com.devmillimo.todoapp.data.TodoEntities
import kotlinx.coroutines.flow.Flow

class TodorepoImplementation(private val database: TodoDatabase):TodoRepo {
    private val dao = database.todoDao()
    override suspend fun getTodos(): Flow<List<TodoEntities>> = dao.getTodos()

    override suspend fun addTodo(todo: TodoEntities) = dao.addTodo(todo)

    override suspend fun updateTodo(todo: TodoEntities) = dao.updateTodo(todo)

    override suspend fun deleteTodo(todo: TodoEntities) = dao.deleteTodo(todo)

}