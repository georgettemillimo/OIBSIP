package com.devmillimo.todoapp.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface UserDao {

    @Insert
    suspend fun registerUser(users: Users)

    @Query("SELECT * FROM users WHERE emails = :email AND password = :pass")
    suspend fun login(email: String, password: String): Users?
}