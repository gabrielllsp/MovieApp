package com.gabrielalmeida.movieapp.presenter.auth.register

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.gabrielalmeida.movieapp.R
import com.gabrielalmeida.movieapp.databinding.FragmentRegisterBinding
import com.gabrielalmeida.movieapp.presenter.MainActivity
import com.gabrielalmeida.movieapp.util.FirebaseHelper
import com.gabrielalmeida.movieapp.util.StateView
import com.gabrielalmeida.movieapp.util.hideKeyboard
import com.gabrielalmeida.movieapp.util.initToolbar
import com.gabrielalmeida.movieapp.util.isEmailValid
import com.gabrielalmeida.movieapp.util.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private val viewModel: RegisterViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initToolbar(toolbar = binding.toolbar)

        initListeners()
    }

    private fun initListeners() {
        binding.btnRegister.setOnClickListener { validateData() }

        Glide
            .with(requireContext())
            .load(R.drawable.loading)
            .into(binding.progressLoading)
    }

    private fun validateData() {
        val email = binding.editEmail.text.toString()
        val password = binding.editPassword.toString()

        if (email.isEmailValid()) {
            hideKeyboard()
            if (password.isNotEmpty()) {
                register(email, password)

            } else {
                showSnackBar(
                    message = R.string.text_password_empty_register_fragment
                )
            }
        } else {
            showSnackBar(
                message = R.string.text_email_empty_register_fragment
            )
        }
    }

    private fun register(email: String, password: String) {
        viewModel.register(email, password).observe(viewLifecycleOwner) { stateView ->
            when (stateView) {

                is StateView.Loading -> {
                    binding.progressLoading.isVisible = true
                }

                is StateView.Success -> {
                    startActivity(Intent(requireContext(), MainActivity::class.java))
                    requireActivity().finish()
                }

                is StateView.Error -> {
                    binding.progressLoading.isVisible = false
                    showSnackBar(
                        message = FirebaseHelper.validError(error = stateView.message ?: "")
                    )
                }
            }

        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}