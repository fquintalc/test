package com.fquintal.test.hilt

import android.app.Application
import com.fquintal.test.remote.DataStorage
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class GlobalModule {

    @Provides
    @Singleton
    fun getGson() : Gson{
        return Gson()
    }

    @Provides
    @Singleton
    fun getStorage(application: Application, gson: Gson): DataStorage {
        return DataStorage(application, gson)
    }
}