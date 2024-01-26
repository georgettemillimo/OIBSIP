package com.devmillimo.todoapp.koin

import android.app.Application
import androidx.room.Room
import com.devmillimo.todoapp.data.TodoDatabase
import com.devmillimo.todoapp.repositories.TodoRepo
import com.devmillimo.todoapp.repositories.TodorepoImplementation
import org.koin.core.context.startKoin
import org.koin.dsl.bind
import org.koin.dsl.module

class KoinApp: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin{
            modules(module {
                single {
                    Room.databaseBuilder(this@KoinApp, TodoDatabase::class.java,"db").build()
                }

                single {
                    TodorepoImplementation(database = get ())
                }bind TodoRepo::class
            })
        }

    }
}