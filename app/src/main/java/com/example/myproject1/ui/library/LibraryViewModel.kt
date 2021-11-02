package com.example.myproject1.ui.library

import android.app.Application
import androidx.lifecycle.*
import com.example.myproject1.data.DatabaseBuilder
import com.example.myproject1.data.Project
import com.example.myproject1.data.ProjectRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class LibraryViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ProjectRepository
    private val _projectListLiveData = MutableLiveData<List<Project>>()
    val projectListLiveData: LiveData<List<Project>>
    get() = _projectListLiveData

    init {
        repository = ProjectRepository(DatabaseBuilder.getInstance(application))
    }

    fun setProjectList() {
        viewModelScope.launch {
            try {
                _projectListLiveData.value = repository.getAllProject()
            } catch (e: Exception) {
                println(e)
            }

        }

    }


}