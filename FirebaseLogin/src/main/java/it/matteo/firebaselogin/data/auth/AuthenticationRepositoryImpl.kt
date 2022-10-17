package it.matteo.firebaselogin.data.auth

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import it.matteo.firebaselogin.data.dtos.LoginCredentialDto
import it.matteo.firebaselogin.data.auth.AuthenticationRepository
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(private val firebaseAuth: FirebaseAuth) :
    AuthenticationRepository {

    override fun isAuthenticated(): Boolean = firebaseAuth.currentUser != null

    override fun login(credential: LoginCredentialDto): Task<AuthResult> {
        return firebaseAuth.signInWithEmailAndPassword(credential.email, credential.password)
    }

    override fun logout() {
        firebaseAuth.signOut()
    }

    override fun resetPassword(email: String) {
        firebaseAuth.sendPasswordResetEmail(email)
    }

    override fun signIn(email: String, password: String): Task<AuthResult> {
        return firebaseAuth.createUserWithEmailAndPassword(email, password)
    }

}