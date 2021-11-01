package com.example.myproject1.ui.drawing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.myproject1.CustomCanvas
import com.example.myproject1.R
import com.example.myproject1.databinding.DrawingFragmentBinding


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
        return binding.root
    }


}