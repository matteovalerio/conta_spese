package it.matteo.contaspese.data.repository.auth

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import it.matteo.contaspese.data.dtos.LoginCredentialDto

interface Authentication {
    fun isAuthenticated(): Boolean
    fun login(credential: LoginCredentialDto): Task<AuthResult>
    fun logout()
}