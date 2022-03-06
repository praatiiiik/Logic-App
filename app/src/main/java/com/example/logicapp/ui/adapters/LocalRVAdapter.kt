package com.example.logicapp.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.logicapp.databinding.LocalRecyclerviewBinding
import com.example.logicapp.local.LocalTable
import com.example.logicapp.local.RemoteTable

class LocalRVAdapter :
    ListAdapter<LocalTable, LocalRVAdapter.ListViewHolder>(DiffUtil()) {


    class ListViewHolder(itemView: LocalRecyclerviewBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val nameTV = itemView.localNameTV
        private val ageTV = itemView.localAgeTv

        @SuppressLint("SetTextI18n")
        fun bind(
            data: LocalTable,

            ) {
            nameTV.text = "Name: ${data.remoteTable?.name.toString()}"
            ageTV.text = "Age: ${data.remoteTable?.age.toString()}"
        }
    }


    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<LocalTable>() {
        override fun areItemsTheSame(
            oldItem: LocalTable,
            newItem: LocalTable
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: LocalTable,
            newItem: LocalTable
        ): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(
            LocalRecyclerviewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

}