package com.example.myproject1.data

class DatabaseHelperImpl(private val projectBd: ProjectBd): DatabaseHelper {
    override suspend fun getAllProject(): List<Project> = projectBd.ProjectDao().getAll()


    override suspend fun insertProject(project: Project) = projectBd.ProjectDao().insertProject(project)

}