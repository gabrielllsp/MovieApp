package com.gabrielalmeida.movieapp.presenter.main.bottombar.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.gabrielalmeida.movieapp.MainGraphDirections
import com.gabrielalmeida.movieapp.R
import com.gabrielalmeida.movieapp.databinding.FragmentSearchBinding
import com.gabrielalmeida.movieapp.presenter.main.bottombar.home.adapter.MovieAdapter
import com.gabrielalmeida.movieapp.util.StateView
import com.gabrielalmeida.movieapp.util.hideKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
        initSearchView()
        initObservers()
    }

    private fun initObservers() {
        stateObserver()
        searchObserver()
    }

    private fun initRecycler() {
        movieAdapter = MovieAdapter(
            context = requireContext(),
            layoutInflater = R.layout.movie_genre_item,
            movieClickListener = { movieId ->
                movieId?.let {
                    val action = MainGraphDirections
                        .actionGlobalMovieDetailsFragment(movieId)
                    findNavController().navigate(action)
                }
            }
        )

        with(binding.recyclerMovies) {
            layoutManager = GridLayoutManager(requireContext(), 2)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

    private fun initSearchView() {
        binding.searchView.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                hideKeyboard()
                if (query.isNotEmpty()) {
                    viewModel.searchMovies(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                Log.d("SimpleSearchView", "Text changed:$newText")
                return false
            }

        })

    }

    private fun stateObserver(){
        viewModel.searchState.observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
                    binding.recyclerMovies.isVisible = false
                    binding.progressBar.isVisible = true
                }

                is StateView.Success -> {
                    binding.progressBar.isVisible = false
                    binding.recyclerMovies.isVisible = true
                }

                is StateView.Error -> {
                    binding.progressBar.isVisible = false

                }
            }

        }
    }

    private fun searchObserver() {
        viewModel.movieList.observe(viewLifecycleOwner){movielist ->
            movieAdapter.submitList(movielist)
            emptyState(movielist.isEmpty())
        }

    }

    private fun emptyState(empty: Boolean){
        binding.recyclerMovies.isVisible = !empty
        binding.layoutEmpty.isVisible = empty
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}