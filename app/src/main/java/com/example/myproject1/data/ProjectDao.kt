package com.example.myproject1.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProjectDao {
    @Query("SELECT * from project")
    suspend fun getAll(): List<Project>

    @Insert()
    suspend fun insertProject(project: Project)

}