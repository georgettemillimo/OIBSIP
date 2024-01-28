package dev.millimo.loginscreen.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo("Fname")
    val first_name: String,
    @ColumnInfo("Lname")
    val last_name: String,
    @ColumnInfo("email")
    val email: String,

)