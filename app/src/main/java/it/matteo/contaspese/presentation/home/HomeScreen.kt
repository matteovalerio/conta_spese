package it.matteo.contaspese.presentation.home

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import it.matteo.contaspese.presentation.Screens

@Composable
fun HomeScreen(navHostController: NavHostController) {
    Button(onClick = {
        Firebase.auth.signOut()
        navHostController.navigate(Screens.Auth.name)
    }) {
        Text(text = "log out")
    }
}