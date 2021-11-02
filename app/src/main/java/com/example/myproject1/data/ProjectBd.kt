package com.example.myproject1.data

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = [Project::class], version = 1)
abstract class ProjectBd : RoomDatabase() {
    abstract fun ProjectDao(): ProjectDao
}