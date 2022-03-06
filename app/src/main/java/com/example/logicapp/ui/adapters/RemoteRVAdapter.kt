package com.example.logicapp.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.logicapp.databinding.RemoteRecyclerViewBinding
import com.example.logicapp.local.RemoteTable

class RemoteRVAdapter :
    ListAdapter<RemoteTable, RemoteRVAdapter.ListViewHolder>(DiffUtil()) {


    class ListViewHolder(itemView: RemoteRecyclerViewBinding) :
        RecyclerView.ViewHolder(itemView.root) {
        private val nameTV = itemView.remoteNameTV
        private val ageTV = itemView.remoteAgeTv

        @SuppressLint("SetTextI18n")
        fun bind(
            data: RemoteTable,

        ) {
            nameTV.text = "Name: ${data.name}"
            ageTV.text = "Age: ${data.age.toString()}"
        }
    }


    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<RemoteTable>() {
        override fun areItemsTheSame(
            oldItem: RemoteTable,
            newItem: RemoteTable
        ): Boolean {
            return oldItem.remoteId == newItem.remoteId
        }

        override fun areContentsTheSame(
            oldItem: RemoteTable,
            newItem: RemoteTable
        ): Boolean {
            return oldItem.remoteId == newItem.remoteId
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(
            RemoteRecyclerViewBinding.inflate(
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