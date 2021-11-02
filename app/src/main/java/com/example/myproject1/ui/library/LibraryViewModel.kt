package com.example.myproject1.ui.library

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myproject1.data.DatabaseBuilder
import com.example.myproject1.data.Project
import com.example.myproject1.data.ProjectRepository

class LibraryViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ProjectRepository
    private val _projectListLiveData = MutableLiveData<List<Project>>()
    val projectListLiveData: LiveData<List<Project>>
    get() = _projectListLiveData

    init {
        repository = ProjectRepository(DatabaseBuilder.getInstance(application))
    }

    fun setProjectList(data: List<Project>) {
        _projectListLiveData.value = data
    }


}