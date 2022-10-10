package it.matteo.contaspese.presentation.auth

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import it.matteo.contaspese.presentation.Screens

@Composable
fun AuthScreen(
    navHostController: NavHostController,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val email = remember { authViewModel.email }
    val password = remember { authViewModel.password }
    val isLoggedIn = remember { authViewModel.isLoggedIn }
    val errorMessage = remember { authViewModel.errorMessage }
    val showLoginScreen = remember { authViewModel.isLoginVisible }

    if (isLoggedIn.value) {
        navHostController.navigate(Screens.Home.name)
    }

    Scaffold(){
        Column(
            modifier = Modifier.fillMaxSize().padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (showLoginScreen.value) {
                LoginTemplate(
                    email.value,
                    password.value,
                    authViewModel::updateUsername,
                    authViewModel::updatePassword,
                    authViewModel::login
                )
            } else {
                SignUpTemplate(
                    email.value,
                    password.value,
                    authViewModel::updateUsername,
                    authViewModel::updatePassword,
                    authViewModel::toggleViewVisibility
                )
            }

            Button(onClick = authViewModel::toggleViewVisibility) {
                Text(
                    text = if (showLoginScreen.value) {
                        "Register"
                    } else {
                        "Show Login"
                    }
                )
            }
        }
        if (errorMessage.value.isNotEmpty()) {
            Toast.makeText(LocalContext.current, errorMessage.value, Toast.LENGTH_SHORT).show()
        }
    }
}

@Composable
fun SignUpTemplate(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSignup: () -> Unit
) {
    Row {
        Text(text = "Email")
        BasicTextField(value = email, onValueChange = onEmailChange)
    }
    TextField(
        value = password,
        onValueChange = onPasswordChange,
        visualTransformation = PasswordVisualTransformation(),
    )
    BasicTextField(
        value = password,
        onValueChange = onPasswordChange,
        visualTransformation = PasswordVisualTransformation()
    )
    Button(onClick = onSignup) {
        Text(text = "Signup")
    }
}

@Composable
fun LoginTemplate(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLogin: () -> Unit
) {
    BasicTextField(value = email, onValueChange = onEmailChange)
    BasicTextField(
        value = password,
        onValueChange = onPasswordChange,
        visualTransformation = PasswordVisualTransformation()
    )
    Button(onClick = onLogin) {
        Text(text = "Login")
    }
}