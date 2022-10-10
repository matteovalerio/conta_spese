package it.matteo.contaspese.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import it.matteo.contaspese.data.repository.auth.Authentication
import it.matteo.contaspese.data.repository.auth.AuthenticationImpl
import kotlin.text.Typography.dagger

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth

    @Provides
    fun provideAuthentication(): Authentication = AuthenticationImpl(Firebase.auth)
}