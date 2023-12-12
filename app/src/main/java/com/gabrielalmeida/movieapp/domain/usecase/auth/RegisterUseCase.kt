package com.gabrielalmeida.movieapp.domain.usecase.auth

import com.gabrielalmeida.movieapp.domain.repository.auth.FirebaseAuthentication

class RegisterUseCase constructor(
    private val firebaseAuthentication: FirebaseAuthentication,
) {
    suspend operator fun invoke(email: String, password: String) {
        firebaseAuthentication.register(email, password)
    }
}