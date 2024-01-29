package com.gabrielalmeida.movieapp.presenter.main.movie_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.gabrielalmeida.movieapp.databinding.FragmentMoviedetailsBinding
import com.gabrielalmeida.movieapp.domain.model.Movie
import com.gabrielalmeida.movieapp.util.StateView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private val args: MovieDetailsFragmentArgs by navArgs()

    private var _binding: FragmentMoviedetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieDetailsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMoviedetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getMovieDetails()
    }

    private fun getMovieDetails(){
        viewModel.getMovieDetails(movieId = args.movieId).observe(viewLifecycleOwner){stateView ->
            when(stateView){

                is StateView.Loading -> {}
                is StateView.Success -> {
                    configData(movie = stateView.data)
                }
                is StateView.Error -> {}
            }
        }
    }

    private fun configData(movie: Movie?){

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}