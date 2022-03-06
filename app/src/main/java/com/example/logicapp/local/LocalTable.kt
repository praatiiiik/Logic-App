package com.example.logicapp.local

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "localTable")
data class LocalTable(@Embedded val remoteTable: RemoteTable? , val toUpload : Int?){
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null
}
