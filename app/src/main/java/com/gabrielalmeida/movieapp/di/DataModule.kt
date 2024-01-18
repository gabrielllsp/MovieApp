package com.gabrielalmeida.movieapp.di

import com.gabrielalmeida.movieapp.data.api.ServiceApi
import com.gabrielalmeida.movieapp.network.ServiceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    fun providesServiceProvider() = ServiceProvider()

    @Provides
    fun providerServiceApi(
        serviceProvider: ServiceProvider
    ): ServiceApi{
        return serviceProvider.createService(ServiceApi::class.java)
    }
}