package com.example.myproject1.data

interface DatabaseHelper {

    suspend fun getAllProject(): List<Project>

    suspend fun insertProject(project: Project)
}