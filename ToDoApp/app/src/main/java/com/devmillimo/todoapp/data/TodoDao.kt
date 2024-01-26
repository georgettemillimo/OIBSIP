package com.devmillimo.todoapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow


@Dao
interface TodoDao {

    @Insert
    fun addTodo(todo: TodoEntities)

    @Query("SELECT * FROM 'todos'")
    fun getTodos():Flow<List<TodoEntities>>

    @Update
    fun updateTodo(todo: TodoEntities)

    @Delete
    fun deleteTodo(todo: TodoEntities)

}