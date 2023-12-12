package com.gabrielalmeida.movieapp.di

import com.gabrielalmeida.movieapp.data.repository.auth.FirebaseAuthenticationImpl
import com.gabrielalmeida.movieapp.domain.repository.auth.FirebaseAuthentication
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindsFirebaseAuthenticationImpl(
        bindsFirebaseAuthenticationImpl: FirebaseAuthenticationImpl,
    ): FirebaseAuthentication
}