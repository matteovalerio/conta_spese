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

    var currentPage: MutableState<AuthScreenName> = mutableStateOf(AuthScreenName.Login)
        private set

    fun updateEmail(updatedValue: String) {
        email.value = updatedValue
    }

    fun updatePassword(updatedValue: String) {
        password.value = updatedValue
    }

    fun setCurrentPage(page: AuthScreenName) {
        currentPage.value = page
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

    fun resetPassword(email: String) {
        authentication.resetPassword(email)
    }

    fun signIn() {
        authentication.signIn(email.value, password.value)
            .addOnSuccessListener {
                setCurrentPage(AuthScreenName.Login)
            }
            .addOnFailureListener {
                val message = it.message.toString()
                if (message != errorMessage.value) {
                    errorMessage.value = it.message.toString()
                }
            }
    }
}