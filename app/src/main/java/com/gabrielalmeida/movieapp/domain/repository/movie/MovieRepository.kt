package com.gabrielalmeida.movieapp.domain.repository.movie

import com.gabrielalmeida.movieapp.data.model.GenresResponse
import com.gabrielalmeida.movieapp.data.model.MovieResponse

interface MovieRepository{

    suspend fun getGenres(apiKey: String, language: String?): GenresResponse
    suspend fun getMovieByGenre(
        apiKey: String,
        language: String?,
        genreId: Int?,
    ): List<MovieResponse>

    suspend fun searchMovies(
        apiKey: String,
        language: String?,
        query: String?,
    ): List<MovieResponse>

    suspend fun getMovieDetails(
        apiKey: String,
        language: String?,
        movieId: Int?,
    ):MovieResponse
}