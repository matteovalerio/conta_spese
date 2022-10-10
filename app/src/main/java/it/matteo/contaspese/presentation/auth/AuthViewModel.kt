package it.matteo.contaspese.presentation.auth

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import it.matteo.contaspese.data.dtos.LoginCredentialDto
import it.matteo.contaspese.data.repository.auth.Authentication
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val authentication: Authentication): ViewModel() {
    var email: MutableState<String> = mutableStateOf("")
        private set

    var password: MutableState<String> = mutableStateOf("")
        private set

    var isLoggedIn: MutableState<Boolean> = mutableStateOf(false)
        private set

    var errorMessage: MutableState<String> = mutableStateOf("")
        private set

    var isLoginVisible: MutableState<Boolean> = mutableStateOf(true)
        private set

    fun updateUsername(updatedValue: String) {
        email.value = updatedValue
    }

    fun updatePassword(updatedValue: String) {
        password.value = updatedValue
    }

    fun toggleViewVisibility() {
        isLoginVisible.value = !isLoginVisible.value
    }

    fun login() {
        try {
            authentication.login(LoginCredentialDto(email.value, password.value))
                .addOnSuccessListener {
                    isLoggedIn.value = true
                }
                .addOnFailureListener {
                    errorMessage.value = it.message.toString()
                }
        } catch (exception: Exception) {
            errorMessage.value = exception.message.toString()
        }
    }
}