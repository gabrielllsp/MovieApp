package com.gabrielalmeida.movieapp.domain.repository.movie

import com.gabrielalmeida.movieapp.data.model.CreditResponse
import com.gabrielalmeida.movieapp.data.model.MovieResponse
import com.gabrielalmeida.movieapp.data.model.MovieReviewResponse

interface MovieDetailsRepository{


    suspend fun getMovieDetails(
        apiKey: String?,
        language: String?,
        movieId: Int?,
    ): MovieResponse

    suspend fun getCredits(
        apiKey: String?,
        language: String?,
        movieId: Int?,
    ): CreditResponse

    suspend fun getSimilar(
        apiKey: String?,
        language: String?,
        movieId: Int?,
    ): List<MovieResponse>

    suspend fun getMovieReviews(
        apiKey: String?,
        language: String?,
        movieId: Int?,
    ): List<MovieReviewResponse>
}