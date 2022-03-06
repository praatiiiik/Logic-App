package com.example.logicapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.logicapp.R
import com.example.logicapp.data.viewmodel.AppViewModel
import com.example.logicapp.databinding.ActivityLocalDataBinding
import com.example.logicapp.databinding.ActivityRemoteDataBinding
import com.example.logicapp.ui.adapters.LocalRVAdapter
import com.example.logicapp.ui.adapters.RemoteRVAdapter
import com.example.logicapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RemoteDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRemoteDataBinding
    private val model : AppViewModel by viewModels()
    private lateinit var remoteAdapter : RemoteRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRemoteDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * initialise adapter
         */
        remoteAdapter = RemoteRVAdapter()
        /**
         * calling to get remote data
         */
        model.getDataFromRemote()

        /**
         * initialise recycler view
         */
        binding.remoteRV.apply {
            this.layoutManager = LinearLayoutManager(this@RemoteDataActivity)
            this.adapter = remoteAdapter
        }

        model.remoteData.observe(this){
            when(it){
                is Resource.Success -> {
                    Log.d("rvadap",it.data.toString())
                    remoteAdapter.submitList(it.data.toMutableList())
                }
            }
        }
    }
}