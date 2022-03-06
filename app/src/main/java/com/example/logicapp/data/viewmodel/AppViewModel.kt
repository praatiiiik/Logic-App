package com.example.logicapp.data.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.logicapp.data.repo.MainRepo
import com.example.logicapp.local.LocalTable
import com.example.logicapp.local.RemoteTable
import com.example.logicapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(private val mainRepo: MainRepo):ViewModel() {

    /**
     * live data variable from local data
     */
    private val _localData : MutableLiveData<Resource<List<LocalTable>>> = MutableLiveData()
    val localData : LiveData<Resource<List<LocalTable>>> = _localData

    /**
     * live data variable from remote data
     */
    private val _remoteData : MutableLiveData<Resource<List<RemoteTable>>> = MutableLiveData()
    val remoteData : LiveData<Resource<List<RemoteTable>>> = _remoteData

    /**
     * get data from local and update live data
     */
    fun getDataFromLocal(){
        viewModelScope.launch(Dispatchers.IO) {
            mainRepo.getAllDataFromLocal().collect {
                _localData.postValue(it)
            }
        }
    }

    /**
     * get data from remote and update live data
     */
    fun getDataFromRemote(){
        viewModelScope.launch(Dispatchers.IO) {
            mainRepo.getAllDataFromRemote().collect {
                _remoteData.postValue(it)
            }
        }
    }

    /**
     * insert local data
     */
    fun insertLocalData(data : LocalTable){
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("logicApp","vm local $data")
            mainRepo.insertLocalData(data)
        }
    }

    /**
     * insert data to remote
     */
    fun insertRemoteData(data : RemoteTable){
        viewModelScope.launch(Dispatchers.IO) {
            Log.d("logicApp","vm remote $data")
            mainRepo.insertRemoteData(data)
        }
    }

    /**
     * update local and remote table
     */
    fun updateRemote(){
        viewModelScope.launch(Dispatchers.IO) {
            mainRepo.updateRemoteData()
        }
    }
}