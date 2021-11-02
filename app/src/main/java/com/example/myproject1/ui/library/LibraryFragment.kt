package com.example.myproject1.ui.library

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject1.R
import com.example.myproject1.adapters.LibraryRecyclerAdapter
import com.example.myproject1.data.Project
import com.example.myproject1.databinding.LibraryFragmentBinding
import com.example.myproject1.ui.SharingDataViewModel
import com.example.myproject1.ui.drawing.DrawingViewModel


class LibraryFragment : Fragment(), LibraryRecyclerAdapter.OnItemClickListener {
    private val TAG = "LibraryFragment"
    private lateinit var navController: NavController
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: LibraryRecyclerAdapter
    private lateinit var viewModel: LibraryViewModel
    private val sharingDataViewModel : SharingDataViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController = findNavController()
        viewModel = ViewModelProvider(this).get(LibraryViewModel::class.java)
        val binding = LibraryFragmentBinding.inflate(inflater, container, false)
        recyclerView = binding.projectsLibraryRecyclerview
        recyclerView.layoutManager = GridLayoutManager(context, 1)
        viewModel.projectListLiveData.observe(viewLifecycleOwner, Observer {
            adapter = LibraryRecyclerAdapter(it, this)
            recyclerView.adapter = adapter
        })


        return binding.root
    }



    override fun onItemClick(position: Int, projectList: List<Project>) {
        if (position == 0) {
            sharingDataViewModel.isNewProject.value = true

        } else {
            sharingDataViewModel.isNewProject.value = false
           sharingDataViewModel.setProjectLiveData(projectList[position])
           sharingDataViewModel.setCountProjectLiveData(position)
        }
        navController.navigate(R.id.action_libraryFragment_to_drawingFragment)
        Log.d(TAG, "Click for transaction between fragments")
    }


}