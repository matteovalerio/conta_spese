package it.matteo.contaspese

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import it.matteo.contaspese.presentation.Screens
import it.matteo.contaspese.presentation.home.HomeScreen
import it.matteo.contaspese.presentation.splash.SplashScreen
import it.matteo.contaspese.ui.theme.ContaSpeseTheme
import it.matteo.firebaselogin.presentation.auth.AuthScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContaSpeseApp()
        }
    }
}

@Composable
fun ContaSpeseApp() {
    val navHostController = rememberNavController()
    ContaSpeseTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            NavHost(navController = navHostController, startDestination = Screens.Splash.name) {
                composable(Screens.Home.name) { HomeScreen(navHostController) }
                composable(Screens.Splash.name) { SplashScreen(navHostController) }
                composable(Screens.Auth.name) { AuthScreen({navHostController.navigate(Screens.Home.name)}) }
            }
        }
    }
}