package com.example.logicapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.logicapp.R
import com.example.logicapp.data.viewmodel.AppViewModel
import com.example.logicapp.databinding.ActivityLocalDataBinding
import com.example.logicapp.ui.adapters.LocalRVAdapter
import com.example.logicapp.utils.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LocalDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLocalDataBinding
    private val model : AppViewModel by viewModels()
    private lateinit var localAdapter : LocalRVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocalDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /**
         * initialise adapter
         */
        localAdapter = LocalRVAdapter()
        /**
         * calling to get data
         */
        model.getDataFromLocal()

        /**
         * initialise recycler view
         */
        binding.localRV.apply {
            this.layoutManager = LinearLayoutManager(this@LocalDataActivity)
            this.adapter = localAdapter
        }

        model.localData.observe(this){
            when(it){
                is Resource.Success -> {
                    Log.d("rvadap",it.data.toString())
                    localAdapter.submitList(it.data.toMutableList())
                }
            }
        }
    }
}