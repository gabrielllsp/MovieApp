package com.gabrielalmeida.movieapp.data.api

import com.gabrielalmeida.movieapp.data.model.BasePaginationRemote
import com.gabrielalmeida.movieapp.data.model.GenreResponse
import com.gabrielalmeida.movieapp.data.model.GenresResponse
import com.gabrielalmeida.movieapp.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceApi {

    @GET("genre/movie/list")
    suspend fun getGenres(
        @Query("api_key")apiKey: String,
        @Query("Language") language: String?
        ): GenresResponse

    @GET("discover/movie")
    suspend fun getMoviesByGenre(
        @Query("api_key")apiKey: String,
        @Query("Language") language: String?,
        @Query("with_genres") genreId: Int?,
    ): BasePaginationRemote<List<MovieResponse>>

    @GET("search/movie")
    suspend fun searchMovies(
        @Query("api_key")apiKey: String,
        @Query("Language") language: String?,
        @Query("query") query: String?,
    ): BasePaginationRemote<List<MovieResponse>>
}