package com.example.logicapp.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocalData(data : LocalTable)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateRemoteData(data : List<RemoteTable>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRemoteData(data : RemoteTable)

    @Query("select * from localTable order by id DESC")
    fun getLocalData(): Flow<List<LocalTable>>

    @Query("select * from remoteTable order by remoteId DESC")
    fun getRemoteData(): Flow<List<RemoteTable>>

    @Query("select * from localTable where toUpload = 1")
    suspend fun dataToUpdateRemote(): List<LocalTable>

    @Query("UPDATE localTable SET toUpload =:value  WHERE toUpload = 1")
    fun updateLocalTable(value : Int)

}