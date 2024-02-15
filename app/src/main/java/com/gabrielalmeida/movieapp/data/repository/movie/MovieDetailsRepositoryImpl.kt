package com.gabrielalmeida.movieapp.data.repository.movie

import com.gabrielalmeida.movieapp.data.api.ServiceApi
import com.gabrielalmeida.movieapp.data.model.CreditResponse
import com.gabrielalmeida.movieapp.data.model.MovieResponse
import com.gabrielalmeida.movieapp.data.model.MovieReviewResponse
import com.gabrielalmeida.movieapp.domain.repository.movie.MovieDetailsRepository
import javax.inject.Inject

class MovieDetailsRepositoryImpl @Inject constructor(
    private val serviceApi: ServiceApi
): MovieDetailsRepository {

    override suspend fun getMovieDetails(
        apiKey: String?,
        language: String?,
        movieId: Int?,
    ): MovieResponse {
        return serviceApi.getMovieDetails(
            movieId = movieId,
            language = language,
            apiKey = apiKey
        )
    }

    override suspend fun getCredits(
        apiKey: String?,
        language: String?,
        movieId: Int?,
    ): CreditResponse {
        return serviceApi.getCredits(
            movieId = movieId,
            language = language,
            apiKey = apiKey
        )
    }

    override suspend fun getSimilar(
        apiKey: String?,
        language: String?,
        movieId: Int?,
    ): List<MovieResponse> {
        return serviceApi.getSimilar(
           apiKey = apiKey,
            language = language,
            movieId = movieId
        ).results ?: emptyList()
    }

    override suspend fun getMovieReviews(
        apiKey: String?,
        language: String?,
        movieId: Int?,
    ): List<MovieReviewResponse> {
        return serviceApi.getMovieReviews(
            apiKey = apiKey,
            language = language,
            movieId = movieId
        ).results ?: emptyList()
    }
}