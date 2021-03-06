package com.example.myproject1.ui.drawing

import android.app.Application
import android.graphics.Bitmap
import android.os.Environment
import androidx.lifecycle.*
import com.example.myproject1.data.DatabaseBuilder
import com.example.myproject1.data.Project
import com.example.myproject1.data.ProjectRepository
import kotlinx.coroutines.launch
import java.io.*
import java.lang.Exception
import android.graphics.BitmapFactory




class DrawingViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ProjectRepository
    private val _isProjectExistsLiveData = MutableLiveData<Boolean>()

    private val _projectsCountLiveData = MutableLiveData<Int>()

    private val _currentProjectLiveData = MutableLiveData<Project>()


    init {
        repository = ProjectRepository(DatabaseBuilder.getInstance(application))
    }


    fun saveImageExternal(bitmap: Bitmap) {
        if (_isProjectExistsLiveData.value == true) {
            val imageName = _currentProjectLiveData.value?.imageName
            val file = File("${_currentProjectLiveData.value?.path}/$imageName")
            val os: FileOutputStream
            try {
                file.createNewFile()
                os = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, os)
                os.flush()
                os.close()
            } catch (e: Exception) {
                println(e)
            }
        } else {
            val count = _projectsCountLiveData.value
            val id = 1 + count!!
            val imageName = "image$id.png"
            val path = Environment.getExternalStorageDirectory().absolutePath
            val file = File("$path/$imageName")
            val os: FileOutputStream
            try {
                file.createNewFile()
                os = FileOutputStream(file)
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, os)
                os.flush()
                os.close()
                _currentProjectLiveData.value = Project((id), "Мой проект $id", imageName, path)
                saveProjectInDatabase()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    private fun saveProjectInDatabase() {
        viewModelScope.launch {
            try {
                _currentProjectLiveData.value?.let { repository.insertProject(it) }
            } catch (e: Exception) {
                println(e)
            }

        }

    }

    fun setProjectExists(boolean: Boolean) {
        _isProjectExistsLiveData.value = boolean
    }

    fun setProjectsCount(int: Int) {
        _projectsCountLiveData.value = int
        println("setProjectsCount $int")
    }

    fun setCurrentProject(project: Project) {
        _currentProjectLiveData.value = project
    }
}
