package com.devmillimo.todoapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class Users(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo("firstName")
    val first_name: String,
    @ColumnInfo("secondName")
    val second_name: String,
    @ColumnInfo("email")
    val email: String,
    @ColumnInfo("pass")
    val password: String,
)