package com.example.logicapp.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [LocalTable::class,RemoteTable::class], exportSchema = false, version = 1)
abstract class AppDatabase:RoomDatabase() {

    abstract fun getDao() : AppDao

    companion object {
        private const val DB_NAME = "logicApp_database"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        /**
         * Checking if the instance exists or not
         * If yes, then return it
         * else generate one
         */

        fun getInstance(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }

            synchronized(this){
                // Creating the instance of the Database
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}