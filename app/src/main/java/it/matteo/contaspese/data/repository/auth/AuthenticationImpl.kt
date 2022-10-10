package it.matteo.contaspese.data.repository.auth

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import it.matteo.contaspese.data.dtos.LoginCredentialDto
import javax.inject.Inject

class AuthenticationImpl @Inject constructor(private val firebaseAuth: FirebaseAuth) :
    Authentication {

    override fun isAuthenticated(): Boolean = firebaseAuth.currentUser != null

    override fun login(credential: LoginCredentialDto): Task<AuthResult> {
        return firebaseAuth.signInWithEmailAndPassword(credential.email, credential.password)
    }

    override fun logout() {
        firebaseAuth.signOut()
    }

}