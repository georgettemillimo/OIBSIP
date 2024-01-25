package com.devmillimo.todoapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Users::class], version = 1)
abstract class UserDatabase : RoomDatabase () {

    abstract val dao: UserDao

}