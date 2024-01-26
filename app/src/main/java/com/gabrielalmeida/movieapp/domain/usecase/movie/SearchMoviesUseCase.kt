package com.gabrielalmeida.movieapp.domain.usecase.movie

import com.gabrielalmeida.movieapp.data.mapper.toDomain
import com.gabrielalmeida.movieapp.domain.model.Movie
import com.gabrielalmeida.movieapp.domain.repository.movie.MovieRepository
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    private val repository: MovieRepository
){
    suspend operator fun invoke(apiKey: String, language: String?, query: String?): List<Movie>{
        return repository.searchMovies(
            apiKey = apiKey,
            language = language,
            query = query
        ).filter { it.backdropPath != null }.map { it.toDomain() }
    }
}