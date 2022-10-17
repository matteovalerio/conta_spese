package it.matteo.firebaselogin.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import it.matteo.firebaselogin.data.auth.AuthenticationRepositoryImpl
import it.matteo.firebaselogin.data.user.UserRepository
import it.matteo.firebaselogin.data.user.UserRepositoryImpl
import it.matteo.firebaselogin.data.auth.AuthenticationRepository

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth

    @Provides
    fun provideAuthentication(): AuthenticationRepository = AuthenticationRepositoryImpl(Firebase.auth)

    @Provides
    fun provideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    fun provideUserRepository(): UserRepository = UserRepositoryImpl(Firebase.firestore)
}