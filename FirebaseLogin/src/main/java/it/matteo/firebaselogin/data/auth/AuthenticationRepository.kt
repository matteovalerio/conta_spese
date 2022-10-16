package it.matteo.firebaselogin.data.auth

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import it.matteo.firebaselogin.data.dtos.LoginCredentialDto

interface AuthenticationRepository {
    fun isAuthenticated(): Boolean
    fun login(credential: LoginCredentialDto): Task<AuthResult>
    fun logout()
    fun resetPassword(email: String)
    fun signIn(email: String, password: String): Task<AuthResult>
}