package com.gabrielalmeida.movieapp.presenter.main.bottombar.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gabrielalmeida.movieapp.databinding.FragmentSearchBinding
import com.gabrielalmeida.movieapp.util.StateView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSearchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun searchMovies(query: String?) {

        viewModel.searchMovies(query).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {
                is StateView.Loading -> {
//                    binding.recyclerMovies.isVisible = false
//                    binding.progressBar.isVisible = true
                }

                is StateView.Success -> {
//                    binding.progressBar.isVisible = false
//                    movieAdapter.submitList(stateView.data)
//                    binding.recyclerMovies.isVisible = true
                }

                is StateView.Error -> {
//                    binding.progressBar.isVisible = false

                }
            }


        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}