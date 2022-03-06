package com.example.logicapp.data.repo

import android.util.Log
import com.example.logicapp.local.LocalTable
import com.example.logicapp.local.RemoteTable
import com.example.logicapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * medium of conversation for local repo and remote repo
 */

interface MainRepo {
    suspend fun getAllDataFromLocal(): Flow<Resource<List<LocalTable>>>
    suspend fun getAllDataFromRemote(): Flow<Resource<List<RemoteTable>>>
    suspend fun insertLocalData(data: LocalTable)
    suspend fun insertRemoteData(data: RemoteTable)
    suspend fun updateRemoteData()
}

 class DefaultMainRepo @Inject constructor(
    private val mainRemoteRepo: MainRemoteRepo,
    private val mainLocalRepo: MainLocalRepo
):MainRepo {
     override suspend fun getAllDataFromLocal(): Flow<Resource<List<LocalTable>>> {
         return mainLocalRepo.getAllData()
     }

     override suspend fun getAllDataFromRemote(): Flow<Resource<List<RemoteTable>>> {
        return mainRemoteRepo.getAllRemoteData()
     }

     override suspend fun insertLocalData(data: LocalTable) {
         Log.d("logicApp","repo local $data")
         mainLocalRepo.insertData(data)
     }

     override suspend fun insertRemoteData(data: RemoteTable) {
         Log.d("logicApp","repo remote $data")
         mainRemoteRepo.insertRemoteData(data)
     }

     override suspend fun updateRemoteData() {
         when(val data = mainLocalRepo.getDataToUpload()){
             is Resource.Success -> {
                 val aList = ArrayList<RemoteTable>()
                 for(item in data.data){
                     item.remoteTable?.let { aList.add(it) }
                 }
                 mainRemoteRepo.updateRemoteData(aList)
                 mainLocalRepo.updateLocalTable()
             }
         }
     }
 }