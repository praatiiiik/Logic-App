package com.example.logicapp.data.repo

import android.util.Log
import com.example.logicapp.local.AppDao
import com.example.logicapp.local.RemoteTable
import com.example.logicapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface MainRemoteRepo {
    suspend fun getAllRemoteData(): Flow<Resource<List<RemoteTable>>>
    suspend fun updateRemoteData(data:List<RemoteTable>)
    suspend fun insertRemoteData(data:RemoteTable)
}

class DefaultMainRemoteRepo @Inject constructor(private val dao:AppDao):MainRemoteRepo{

    /**
     * get data from remote
     */
    override suspend fun getAllRemoteData(): Flow<Resource<List<RemoteTable>>> = flow {
        emitAll(
            dao.getRemoteData().map {
                Resource.Success(it)
            }
        )
    }

    /**
     * update data from local when comes online
     */
    override suspend fun updateRemoteData(data: List<RemoteTable>) {
        dao.updateRemoteData(data)
    }

    /**
     * insert data when app in online
     */
    override suspend fun insertRemoteData(data: RemoteTable) {
        Log.d("logicApp","remote remote $data")
        dao.insertRemoteData(data)
    }

}