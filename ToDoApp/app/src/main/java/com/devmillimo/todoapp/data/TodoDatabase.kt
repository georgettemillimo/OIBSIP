package com.devmillimo.todoapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TodoEntities::class], version = 1)
abstract class TodoDatabase: RoomDatabase (){

    abstract fun todoDao(): TodoDao

}