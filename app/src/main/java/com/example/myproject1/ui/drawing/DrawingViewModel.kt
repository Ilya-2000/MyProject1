package com.example.myproject1.ui.drawing

import android.graphics.Bitmap
import android.os.Environment
import androidx.lifecycle.ViewModel
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception

class DrawingViewModel : ViewModel() {

    fun saveImage(bitmap: Bitmap) {
        val path = Environment.getExternalStorageDirectory().absolutePath
        val file = File("$path/image.png")
        val os: FileOutputStream
        try {
            file.createNewFile()
            os = FileOutputStream(file)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, os)
            os.flush()
            os.close()
            println("save success")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getFileName() {

    }
}