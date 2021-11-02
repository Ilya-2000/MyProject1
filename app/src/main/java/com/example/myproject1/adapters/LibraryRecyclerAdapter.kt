package com.example.myproject1.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myproject1.R
import com.example.myproject1.data.Project
import com.example.myproject1.databinding.AddProjectLayoutBinding
import com.example.myproject1.databinding.PlateBinding


class LibraryRecyclerAdapter(private val projectList: ArrayList<Project>, private val onItemClickListener: OnItemClickListener, private val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    companion object {
        const val VIEW_TYPE_ONE = 1
        const val VIEW_TYPE_TWO = 2
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == VIEW_TYPE_ONE) {
            val view: AddProjectLayoutBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.add_project_layout,
                parent, false)
            AddProjectViewHolder(view)
        } else {
            val view: PlateBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.project_plate_layout,
                parent, false)
            ProjectViewHolder(view)
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(position == 0) {
            Log.d("Adapter1", "position $position projectSize: ${projectList.size}")
            (holder as AddProjectViewHolder).bind(onItemClickListener)


        } else {
            Log.d("Adapter2", "position $position projectSize: ${projectList.size}")
            (holder as ProjectViewHolder).bind(position, onItemClickListener, projectList[position - 1])
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(position) {
            0 -> VIEW_TYPE_ONE
            else -> VIEW_TYPE_TWO
        }
    }

    override fun getItemCount(): Int = projectList.size + 1

    private inner class ProjectViewHolder(private val plateBinding: PlateBinding): RecyclerView.ViewHolder(plateBinding.root) {
        fun bind(position: Int, onItemClickListener: OnItemClickListener, item: Project) = with(plateBinding) {
            plateBinding.name = item.name
            Log.d("PlateName", item.name)
            Glide.with(context)
                .load(item.path + "/" + item.id)
                .into(plateBinding.plateImg)
            plateBinding.root.setOnClickListener {
                onItemClickListener.onItemClick(position, projectList)
            }
        }
    }

    private inner class AddProjectViewHolder(private val addProjectLayoutBinding: AddProjectLayoutBinding): RecyclerView.ViewHolder(addProjectLayoutBinding.root) {
        fun bind(onItemClickListener: OnItemClickListener) = with(addProjectLayoutBinding) {
            addProjectLayoutBinding.root.setOnClickListener {
                onItemClickListener.onItemClick(0, projectList)
            }
        }
    }
    interface OnItemClickListener {
        fun onItemClick(position: Int, projectList: List<Project>)
    }

    fun addProjects(data: List<Project>) {
        projectList.addAll(data)
    }

}