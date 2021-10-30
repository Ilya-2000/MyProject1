package com.example.myproject1.ui.library

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.myproject1.R
import com.example.myproject1.databinding.LibraryFragmentBinding


class LibraryFragment : Fragment() {
    private var _binding: LibraryFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController = findNavController()
        _binding = LibraryFragmentBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }



}