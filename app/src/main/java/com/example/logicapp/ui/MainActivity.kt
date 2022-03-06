package com.example.logicapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.MutableLiveData
import com.example.logicapp.R
import com.example.logicapp.data.viewmodel.AppViewModel
import com.example.logicapp.databinding.ActivityMainBinding
import com.example.logicapp.local.LocalTable
import com.example.logicapp.local.RemoteTable
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val model : AppViewModel by viewModels()
    val isChecked:MutableLiveData<Boolean> = MutableLiveData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.switchBut.setOnClickListener {
            isChecked.value = binding.switchBut.isChecked
        }

        binding.saveBut.setOnClickListener {
            val name = binding.nameEt.text.toString()
            val age = binding.ageEt.text.toString()
            if(name.isNotBlank() || age.isNotBlank()){
                if(binding.switchBut.isChecked){
                    model.insertRemoteData(RemoteTable(name,age.toInt()))
                    model.insertLocalData(LocalTable(RemoteTable(name, age.toInt()),0))
                }else{
                    model.insertLocalData(LocalTable(RemoteTable(name, age.toInt()),1))
                }
                Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"Empty Credentials",Toast.LENGTH_SHORT).show()
            }
        }

        /**
         * starts local activity
         */
        binding.showLocalBut.setOnClickListener {
            val intent = Intent(this, LocalDataActivity::class.java)
            startActivity(intent)
        }

        /**
         * starts remote activity
         */
        binding.showRemote.setOnClickListener {
            val intent = Intent(this, RemoteDataActivity::class.java)
            startActivity(intent)
        }

        /**
         * update automatically when device is online
         */
        isChecked.observe(this){
            if(it){
                model.updateRemote()
                Toast.makeText(this,"Online",Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"Offline",Toast.LENGTH_SHORT).show()
            }
        }
    }
}