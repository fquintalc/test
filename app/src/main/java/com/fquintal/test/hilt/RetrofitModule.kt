package com.fquintal.test.hilt

import com.fquintal.test.remote.service.FinerioService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {

    @Provides
    @Singleton
    fun getDefaultRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl("https://api.finerio.mx/").addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun getFinerioService(retrofit: Retrofit) : FinerioService {
        return retrofit.create(FinerioService::class.java)
    }



}