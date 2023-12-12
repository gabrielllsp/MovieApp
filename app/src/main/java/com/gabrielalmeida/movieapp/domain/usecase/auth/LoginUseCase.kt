package com.gabrielalmeida.movieapp.domain.usecase.auth

import com.gabrielalmeida.movieapp.domain.repository.auth.FirebaseAuthentication

class LoginUseCase constructor(
    private val firebaseAuthentication: FirebaseAuthentication,
) {
    suspend operator fun invoke(email: String, password: String) {
        firebaseAuthentication.login(email, password)
    }
}