package it.matteo.contaspese.presentation.splash

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import it.matteo.data.auth.AuthenticationRepository
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val authenticationRepository: AuthenticationRepository) : ViewModel() {
    fun isLoggedIn(): Boolean = authenticationRepository.isAuthenticated()
}