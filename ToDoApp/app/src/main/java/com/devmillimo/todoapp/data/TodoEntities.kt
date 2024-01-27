package com.devmillimo.todoapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.text.SimpleDateFormat
import java.util.*

@Entity(tableName = "todos")
data class TodoEntities(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo("title")
    val title: String,
    @ColumnInfo("sub_title")
    val subTitle: String,
    @ColumnInfo("done")
    val done: Boolean = false,
    @ColumnInfo("added")
    val added: Long = System.currentTimeMillis(),

)

val TodoEntities.aaDate: String get() = SimpleDateFormat("yyyy/mm/dd hh:mm").format(Date(added))
