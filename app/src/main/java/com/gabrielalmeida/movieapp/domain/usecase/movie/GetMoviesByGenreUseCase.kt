package com.gabrielalmeida.movieapp.domain.usecase.movie

import com.gabrielalmeida.movieapp.data.mapper.toDomain
import com.gabrielalmeida.movieapp.domain.model.Movie
import com.gabrielalmeida.movieapp.domain.repository.movie.MovieRepository
import javax.inject.Inject

class GetMoviesByGenreUseCase @Inject constructor(
    private val repository: MovieRepository
){
    suspend operator fun invoke(apiKey: String, language: String?, genreId: Int?): List<Movie>{
        return repository.getMovieByGenre(
            apiKey = apiKey,
            language = language,
            genreId = genreId
        ).map { it.toDomain() }
    }
}