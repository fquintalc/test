package com.fquintal.test.hilt

import com.fquintal.domain.datasource.LoginDataSource
import com.fquintal.domain.impl.DefaultLoginRepository
import com.fquintal.domain.repository.LoginRepository
import com.fquintal.usecase.LoginUseCase
import com.fquintal.test.remote.DataStorage
import com.fquintal.test.remote.datasources.RemoteLoginDataSource
import com.fquintal.test.remote.service.FinerioService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LoginModule {

    private fun getRemoteLoginDataSource(
        finerioService: FinerioService,
        dataStorage: DataStorage
    ): LoginDataSource {
        return RemoteLoginDataSource(finerioService, dataStorage)
    }

    @Provides
    @Singleton
    fun getLoginRepository(finerioService: FinerioService, dataStorage: DataStorage) : LoginRepository {
        return DefaultLoginRepository(getRemoteLoginDataSource(finerioService, dataStorage))
    }

    @Provides
    @Singleton
    fun getLoginUseCase(loginRepository: LoginRepository): LoginUseCase {
        return LoginUseCase(loginRepository)
    }
}