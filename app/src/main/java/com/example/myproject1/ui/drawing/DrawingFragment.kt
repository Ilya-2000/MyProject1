package com.example.myproject1.ui.drawing

import android.graphics.Bitmap
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.myproject1.databinding.DrawingFragmentBinding
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.myproject1.R
import com.example.myproject1.ui.SharingDataViewModel


class DrawingFragment : Fragment() {
    private lateinit var navController: NavController
    private lateinit var customCanvas: CustomCanvas
    private lateinit var viewModel: DrawingViewModel
    private val sharingDataViewModel: SharingDataViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController = findNavController()
        customCanvas = CustomCanvas(context, 30f)
        val binding = DrawingFragmentBinding.inflate(inflater, container, false)
        viewModel =
            ViewModelProvider(this).get(DrawingViewModel::class.java)
        checkingProject()

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
            navController.navigate(R.id.action_drawingFragment_to_libraryFragment)
        }
        return binding.root
    }

    private fun checkingProject() {
        if(sharingDataViewModel.isNewProject.value == true) {
            sharingDataViewModel.projectCountLiveData.value?.let { viewModel.setProjectsCount(it) }
        } else {
            sharingDataViewModel.projectCountLiveData.value?.let { viewModel.setProjectsCount(it) }
            sharingDataViewModel.projectLiveData.value?.let { viewModel.setCurrentProject(it) }
            viewModel.setProjectExists(true)
            viewModel.getDataFromFile()
        }
    }





}