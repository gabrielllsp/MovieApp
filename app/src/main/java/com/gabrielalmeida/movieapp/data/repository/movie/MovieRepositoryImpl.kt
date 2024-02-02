package com.gabrielalmeida.movieapp.data.repository.movie

import com.gabrielalmeida.movieapp.data.api.ServiceApi
import com.gabrielalmeida.movieapp.data.model.GenresResponse
import com.gabrielalmeida.movieapp.data.model.MovieResponse
import com.gabrielalmeida.movieapp.domain.repository.movie.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val serviceApi: ServiceApi
):MovieRepository{

    override suspend fun getGenres(apiKey: String?, language: String?): GenresResponse {
        return serviceApi.getGenres(
            apiKey = apiKey,
            language = language
        )
    }

    override suspend fun getMovieByGenre(
        apiKey: String?,
        language: String?,
        genreId: Int?,
    ): List<MovieResponse> {
        return serviceApi.getMoviesByGenre(
            apiKey = apiKey,
            language = language,
            genreId = genreId
        ).results ?: emptyList()
    }

    override suspend fun searchMovies(
        apiKey: String?,
        language: String?,
        query: String?,
    ): List<MovieResponse> {
        return serviceApi.searchMovies(
            apiKey = apiKey,
            language = language,
            query = query
        ).results ?: emptyList()
    }

}