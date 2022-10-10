package it.matteo.contaspese.presentation.splash

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import it.matteo.contaspese.data.repository.auth.Authentication
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(private val authentication: Authentication) : ViewModel() {
    fun isLoggedIn(): Boolean = authentication.isAuthenticated()
}