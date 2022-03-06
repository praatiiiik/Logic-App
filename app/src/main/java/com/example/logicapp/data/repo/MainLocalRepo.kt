package com.example.logicapp.data.repo

import android.util.Log
import com.example.logicapp.local.AppDao
import com.example.logicapp.local.LocalTable
import com.example.logicapp.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface MainLocalRepo {
    suspend fun getAllData(): Flow<Resource<List<LocalTable>>>
    suspend fun getDataToUpload():Resource<List<LocalTable>>
    suspend fun insertData(data : LocalTable)
    suspend fun updateLocalTable()
}

class DefaultLocalRepo @Inject constructor(private val dao : AppDao):MainLocalRepo{

    /**
     * get data from local.
     */
    override suspend fun getAllData(): Flow<Resource<List<LocalTable>>> = flow {
        emitAll(
            dao.getLocalData().map {
                Resource.Success(it)
            }
        )
    }

    /**
     * get data to upload in remote
     */
    override suspend fun getDataToUpload(): Resource<List<LocalTable>> {
        return Resource.Success( dao.dataToUpdateRemote())
    }

    /**
     * insert data to local
     */
    override suspend fun insertData(data: LocalTable) {
        Log.d("logicApp","local local $data")
        dao.insertLocalData(data)
    }

    /**
     * update local data to avoid repetition of uploading of data
     */
    override suspend fun updateLocalTable() {
        dao.updateLocalTable(0)
    }


}