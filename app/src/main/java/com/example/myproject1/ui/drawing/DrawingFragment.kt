package com.example.myproject1.ui.drawing

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.myproject1.CustomCanvas
import com.example.myproject1.R
import com.example.myproject1.databinding.DrawingFragmentBinding
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream
import android.graphics.Bitmap.CompressFormat

import android.os.Environment
import java.io.File
import java.lang.Exception


class DrawingFragment : Fragment() {
    private lateinit var customCanvas: CustomCanvas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        customCanvas = CustomCanvas(context, 30f)
        val binding = DrawingFragmentBinding.inflate(inflater, container, false)
        binding.canvasLayout.addView(customCanvas)

        binding.undoImageButton.setOnClickListener {
            customCanvas.onUndoClick()
        }

        binding.redoImageButton.setOnClickListener {
            customCanvas.onRedoClick()
        }
        binding.brushBtn.setOnClickListener {
            customCanvas.selectBrush()
        }
        binding.eraserBtn.setOnClickListener {
            customCanvas.selectEraser()
        }

        binding.saveBtn.setOnClickListener {
            binding.canvasLayout.isDrawingCacheEnabled = true
            binding.canvasLayout.setDrawingCacheEnabled(true)
            binding.canvasLayout.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH)
            val bitmap: Bitmap = binding.canvasLayout.getDrawingCache()
            val path = Environment.getExternalStorageDirectory().absolutePath
            val file = File("$path/image.png")
            val ostream: FileOutputStream
            try {
                file.createNewFile()
                ostream = FileOutputStream(file)
                bitmap.compress(CompressFormat.PNG, 100, ostream)
                ostream.flush()
                ostream.close()
                println("save success")
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return binding.root
    }


}