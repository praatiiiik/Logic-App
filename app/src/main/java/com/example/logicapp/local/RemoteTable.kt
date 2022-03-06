package com.example.logicapp.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remoteTable")
data class RemoteTable(val name:String?,val age : Int?) {
    @PrimaryKey(autoGenerate = true)
    var remoteId : Int? = null
}