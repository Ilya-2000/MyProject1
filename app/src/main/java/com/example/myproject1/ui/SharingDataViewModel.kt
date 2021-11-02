package com.example.myproject1.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myproject1.data.Project

class SharingDataViewModel : ViewModel() {
    private val _projectLiveData = MutableLiveData<Project>()
    val projectLiveData: LiveData<Project>
    get() = _projectLiveData

    private val _projectCountLiveData = MutableLiveData<Int>()
    val projectCountLiveData: LiveData<Int>
        get() = _projectCountLiveData

    val isNewProject = MutableLiveData<Boolean>()

    fun setProjectLiveData(data: Project) {
        _projectLiveData.value = data
    }

    fun setCountProjectLiveData(data: Int) {
        _projectCountLiveData.value = data
    }
}