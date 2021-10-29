package com.example.myproject1.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myproject1.R
import com.example.myproject1.databinding.PlateBinding


class LibraryRecyclerAdapter: RecyclerView.Adapter<LibraryRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view: PlateBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.project_plate_layout,
           parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return 0
    }

    inner class ViewHolder(plateBinding: PlateBinding): RecyclerView.ViewHolder(plateBinding.root) {

    }
}