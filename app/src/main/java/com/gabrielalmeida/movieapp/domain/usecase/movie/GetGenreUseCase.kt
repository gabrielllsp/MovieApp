package com.gabrielalmeida.movieapp.domain.usecase.movie

import com.gabrielalmeida.movieapp.data.mapper.toDomain
import com.gabrielalmeida.movieapp.domain.model.Genre
import com.gabrielalmeida.movieapp.domain.repository.movie.MovieRepository
import javax.inject.Inject

class GetGenreUseCase @Inject constructor(
    private val repository: MovieRepository
){
    suspend operator fun invoke(apiKey: String, language: String?): List<Genre>{
        return repository.getGenres(
            apiKey = apiKey,
            language = language
        ).genres?.map { it.toDomain() } ?: emptyList()
    }
}