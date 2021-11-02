package com.example.myproject1.ui.library

import android.app.Application
import androidx.lifecycle.*
import com.example.myproject1.LoadingState
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

    private val _loadingStateLiveData = MutableLiveData<LoadingState>()
    val loadingStateLiveData : LiveData<LoadingState>
    get() = _loadingStateLiveData

    init {
        repository = ProjectRepository(DatabaseBuilder.getInstance(application))
        getProjectList()
    }

    private fun getProjectList() {
        viewModelScope.launch() {
            _loadingStateLiveData.value = LoadingState.LOADING
            try {
                _projectListLiveData.value = repository.getAllProject()
                println(_projectListLiveData.value)
                _loadingStateLiveData.value = LoadingState.LOADED
            } catch (e: Exception) {
                println(e)
                _loadingStateLiveData.value = LoadingState.error(e.message)
            }

        }

    }


}