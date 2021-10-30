package com.example.myproject1.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject1.R
import com.example.myproject1.databinding.AddProjectLayoutBinding
import com.example.myproject1.databinding.PlateBinding


class LibraryRecyclerAdapter(val onItemClickListener: OnItemClickListener): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
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
            (holder as AddProjectViewHolder).bind()

        } else {
            (holder as ProjectViewHolder).bind(position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(position) {
            0 -> VIEW_TYPE_ONE
            else -> VIEW_TYPE_TWO
        }
    }

    override fun getItemCount(): Int {
        return 1
    }

    private inner class ProjectViewHolder(private val plateBinding: PlateBinding): RecyclerView.ViewHolder(plateBinding.root) {
        fun bind(position: Int) = with(plateBinding) {
            plateBinding.root.setOnClickListener {
                onItemClickListener.onItemClick(position)
            }
        }
    }

    private inner class AddProjectViewHolder(private val addProjectLayoutBinding: AddProjectLayoutBinding): RecyclerView.ViewHolder(addProjectLayoutBinding.root) {
        fun bind() = with(addProjectLayoutBinding) {
            addProjectLayoutBinding.root.setOnClickListener {
                onItemClickListener.onItemClick(0)
            }
        }
    }
    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }



}