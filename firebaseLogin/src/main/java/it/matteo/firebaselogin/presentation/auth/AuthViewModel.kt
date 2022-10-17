package it.matteo.firebaselogin.presentation.auth

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import it.matteo.data.auth.AuthenticationRepositoryImpl
import it.matteo.data.dtos.LoginCredentialDto
import it.matteo.data.dtos.UserDto
import it.matteo.data.exceptions.FirestoreException
import it.matteo.data.user.UserRepositoryImpl
import javax.inject.Inject

enum class AuthState {
    Loading,
    Loaded,
    Idle,
    Error,
    Logged
}

@HiltViewModel
class AuthViewModel @Inject constructor(): ViewModel() {
    private val authenticationRepository = AuthenticationRepositoryImpl(Firebase.auth)
    private val userRepository = UserRepositoryImpl(Firebase.firestore)

    var email: MutableState<String> = mutableStateOf("matteovalerio2006@hotmail.it")
        private set

    var password: MutableState<String> = mutableStateOf("pippo1234")
        private set

    var name: MutableState<String> = mutableStateOf("")
        private set

    var isLoggedIn: MutableState<Boolean> = mutableStateOf(false)
        private set

    var errorMessage: MutableState<String> = mutableStateOf("")
        private set

    var currentPage: MutableState<AuthScreenName> = mutableStateOf(AuthScreenName.Login)
        private set

    var state: MutableState<AuthState> = mutableStateOf(AuthState.Idle)
        private set

    fun updateEmail(updatedValue: String) {
        email.value = updatedValue
    }

    fun updatePassword(updatedValue: String) {
        password.value = updatedValue
    }

    fun updateName(updatedValue: String) {
        name.value = updatedValue
    }

    fun updateCurrentPage(page: AuthScreenName) {
        currentPage.value = page
    }

    fun updateState(updatedValue: AuthState) {
        state.value = updatedValue
    }

    fun clearErrors() {
        errorMessage.value = ""
    }

    fun login() {
        if (state.value == AuthState.Logged)
            state.value = AuthState.Loaded

        clearErrors()
        state.value = AuthState.Loading
        try {
            authenticationRepository.login(LoginCredentialDto(email.value, password.value))
                .addOnSuccessListener {
                    isLoggedIn.value = true
                    state.value = AuthState.Logged
                }
                .addOnFailureListener {
                    errorMessage.value = it.message.toString()
                    state.value = AuthState.Error
                }
        } catch (exception: Exception) {
            errorMessage.value = exception.message.toString()
            state.value = AuthState.Error
        }
    }

    fun resetPassword(email: String) {
        authenticationRepository.resetPassword(email)
    }

    fun signIn() {
        state.value = AuthState.Loading
        clearErrors()
        authenticationRepository.signIn(email.value, password.value)
            .addOnSuccessListener {
                try {
                    userRepository.createUser(UserDto(name = name.value, email = email.value))
                    state.value = AuthState.Loaded
                    updateCurrentPage(AuthScreenName.Login)
                } catch (exception: FirestoreException) {
                    state.value = AuthState.Error
                    val message = exception.toString()
                    if (message != errorMessage.value) {
                        errorMessage.value = exception.toString()
                    }
                }
            }
            .addOnFailureListener {
                state.value = AuthState.Error
                val message = it.message.toString()
                if (message != errorMessage.value) {
                    errorMessage.value = it.message.toString()
                }
            }
    }
}
