package com.gabrielalmeida.movieapp.presenter.main.bottombar.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.gabrielalmeida.movieapp.MainGraphDirections
import com.gabrielalmeida.movieapp.databinding.FragmentHomeBinding
import com.gabrielalmeida.movieapp.presenter.main.bottombar.home.adapter.GenreMovieAdapter
import com.gabrielalmeida.movieapp.presenter.model.GenrePresentation
import com.gabrielalmeida.movieapp.util.StateView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var genreMovieAdapter: GenreMovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
        getGenres()

    }

    private fun getGenres() {
        viewModel.getGenres().observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {

                }

                is StateView.Success -> {
                    val genres = stateView.data ?: emptyList()
                    genreMovieAdapter.submitList(genres)
                    getMoviesByGenre(genres)
                }

                is StateView.Error -> {

                }
            }
        }
    }

    private fun getMoviesByGenre(genres: List<GenrePresentation>) {
        val genreMutableList = genres.toMutableList()

        genreMutableList.forEachIndexed{ index, genre ->
            viewModel.getMoviesByGenre(genre.id).observe(viewLifecycleOwner) { stateView ->
                when (stateView) {
                    is StateView.Loading -> {

                    }

                    is StateView.Success -> {
                        genreMutableList[index] = genre.copy(movies = stateView.data?.take(5))
                        lifecycleScope.launch {
                            delay(1000)
                            genreMovieAdapter.submitList(genreMutableList)
                        }

                    }

                    is StateView.Error -> {

                    }
                }
            }
        }

    }

    private fun initRecycler() {
        genreMovieAdapter = GenreMovieAdapter(
            showAllListener = { genreId, name ->
                val action = HomeFragmentDirections
                    .actionMenuHomeToMovieGenreFragment(genreId, name)
                findNavController().navigate(action)
            },
            movieClickListener = {movieId ->
                movieId?.let {
                    val action = MainGraphDirections
                        .actionGlobalMovieDetailsFragment(movieId)
                    findNavController().navigate(action)
                }
            }
        )


        with(binding.recyclerGenres) {
            setHasFixedSize(true)
            adapter = genreMovieAdapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}