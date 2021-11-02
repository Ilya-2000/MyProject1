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
import com.example.myproject1.LoadingState
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
        setupViewModel()
        setupUI()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController = findNavController()
        val binding = LibraryFragmentBinding.inflate(inflater, container, false)
        recyclerView = binding.projectsLibraryRecyclerview
        recyclerView.layoutManager = GridLayoutManager(context, 2)


        viewModel.loadingStateLiveData.observe(viewLifecycleOwner, Observer {
            when(it.status) {
                LoadingState.Status.FAILED -> {
                    setupUI()
                }
                LoadingState.Status.RUNNING -> {
                    Log.d("Status", "Loading...")
                }
                LoadingState.Status.SUCCESS -> {
                    renderList(viewModel.projectListLiveData.value!!)
                }
            }
        })
        recyclerView.adapter = adapter


        return binding.root
    }



    override fun onItemClick(position: Int, projectList: List<Project>) {
        if (position == 0) {
            sharingDataViewModel.isNewProject.value = true
            sharingDataViewModel.setCountProjectLiveData(projectList.size)

        } else {
            sharingDataViewModel.isNewProject.value = false
            sharingDataViewModel.setProjectLiveData(projectList[position - 1])
            sharingDataViewModel.setCountProjectLiveData(projectList.size)
        }
        navController.navigate(R.id.action_libraryFragment_to_drawingFragment)
        Log.d(TAG, "Click for transaction between fragments")
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(LibraryViewModel::class.java)
    }

    private fun renderList(data: List<Project>) {
        adapter.addProjects(data)
        adapter.notifyDataSetChanged()
    }

    private fun setupUI() {
        adapter = LibraryRecyclerAdapter(arrayListOf(), this, requireContext())
    }


}