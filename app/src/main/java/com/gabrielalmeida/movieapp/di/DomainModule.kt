package com.gabrielalmeida.movieapp.di

import com.gabrielalmeida.movieapp.data.repository.auth.FirebaseAuthenticationImpl
import com.gabrielalmeida.movieapp.data.repository.movie.MovieDetailsRepositoryImpl
import com.gabrielalmeida.movieapp.data.repository.movie.MovieRepositoryImpl
import com.gabrielalmeida.movieapp.domain.repository.auth.FirebaseAuthentication
import com.gabrielalmeida.movieapp.domain.repository.movie.MovieDetailsRepository
import com.gabrielalmeida.movieapp.domain.repository.movie.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindsFirebaseAuthenticationImpl(
        firebaseAuthenticationImpl: FirebaseAuthenticationImpl,
    ): FirebaseAuthentication

    @Binds
    abstract fun bindsMovieRepositoryImpl(
        movieRepositoryImpl: MovieRepositoryImpl,
    ): MovieRepository

    @Binds
    abstract fun bindsMovieDetailsRepositoryImpl(
        movieDetailsRepositoryImpl: MovieDetailsRepositoryImpl
    ): MovieDetailsRepository


}