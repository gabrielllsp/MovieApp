package com.gabrielalmeida.movieapp.presenter.main.movie_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.gabrielalmeida.movieapp.BuildConfig
import com.gabrielalmeida.movieapp.domain.usecase.movie.GetSimilarUseCase
import com.gabrielalmeida.movieapp.util.Constants
import com.gabrielalmeida.movieapp.util.StateView
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class SimilarViewModel @Inject constructor(
    private val getSimilarUseCase: GetSimilarUseCase,
) : ViewModel() {

    fun getSimilar(movieId: Int?) = liveData(Dispatchers.IO) {
        try {
            emit(StateView.Loading())

            val movies = getSimilarUseCase(
                apiKey = BuildConfig.API_KEY,
                language = Constants.Movie.LANGUAGE,
                movieId = movieId

            )

            emit(StateView.Success(movies))

        } catch (e: HttpException) {
            e.printStackTrace()
            emit(StateView.Error(message = e.message))
        } catch (e: Exception) {
            e.printStackTrace()
            emit(StateView.Error(message = e.message))
        }
    }

}