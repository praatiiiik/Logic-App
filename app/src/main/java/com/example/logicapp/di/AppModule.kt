package com.example.logicapp.di

import android.app.Application
import android.content.Context
import com.example.logicapp.data.repo.*
import com.example.logicapp.local.AppDao
import com.example.logicapp.local.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesMainRemoteRepo(dao: AppDao): MainRemoteRepo = DefaultMainRemoteRepo(dao)

    @Singleton
    @Provides
    fun providesMainLocalRepo(dao: AppDao): MainLocalRepo = DefaultLocalRepo(dao)

    @Singleton
    @Provides
    fun providesMaineRepo(mainRemoteRepo: MainRemoteRepo, mainLocalRepo: MainLocalRepo): MainRepo =
        DefaultMainRepo(mainRemoteRepo, mainLocalRepo)

    @Singleton
    @Provides
    fun provideQuoteDatabase(application: Application) : AppDatabase =
        AppDatabase.getInstance(application)

    @Singleton
    @Provides
    fun provideQuoteDao(database: AppDatabase) : AppDao = database.getDao()

}