package com.gabrielalmeida.movieapp.domain.usecase.auth

import com.gabrielalmeida.movieapp.domain.repository.auth.FirebaseAuthentication

class ForgotUseCase constructor(
    private val firebaseAuthentication: FirebaseAuthentication,
) {
    suspend operator fun invoke(email: String) {
        firebaseAuthentication.forgot(email)
    }
}