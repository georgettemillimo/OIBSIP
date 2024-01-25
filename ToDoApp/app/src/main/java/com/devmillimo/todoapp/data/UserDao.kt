package com.devmillimo.todoapp.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert


@Dao
interface UserDao {

    @Upsert
    suspend fun upsertUser(user: Users)

    @Query("SELECT * FROM users WHERE email = email")
    suspend fun getUser()

}