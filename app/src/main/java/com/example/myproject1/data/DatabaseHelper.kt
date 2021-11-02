package com.example.myproject1.data

import androidx.lifecycle.LiveData

interface DatabaseHelper {

    suspend fun getAllProject(): List<Project>

    suspend fun insertProject(project: Project)
}