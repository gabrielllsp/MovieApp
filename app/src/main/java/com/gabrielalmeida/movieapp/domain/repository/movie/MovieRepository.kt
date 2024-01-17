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
}