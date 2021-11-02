package com.example.myproject1.ui.drawing

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myproject1.databinding.DrawingFragmentBinding
import androidx.lifecycle.ViewModelProvider


class DrawingFragment : Fragment() {
    private lateinit var customCanvas: CustomCanvas
    private lateinit var viewModel: DrawingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        customCanvas = CustomCanvas(context, 30f)
        val binding = DrawingFragmentBinding.inflate(inflater, container, false)
        viewModel =
            ViewModelProvider(this).get(DrawingViewModel::class.java)

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
            viewModel.saveImage(bitmap)
        }
        return binding.root
    }





}