package com.devmillimo.todoapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Users(
    val firstName: String,
    val secondName: String,
    val email: String,
    val password: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
)
