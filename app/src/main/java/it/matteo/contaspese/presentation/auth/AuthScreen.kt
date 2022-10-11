package it.matteo.contaspese.presentation.auth

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import it.matteo.contaspese.presentation.Screens
import it.matteo.contaspese.presentation.components.atom.TextFieldAtom
import it.matteo.contaspese.presentation.components.template.ForgotPasswordTemplate
import it.matteo.contaspese.presentation.components.template.LoginTemplate
import it.matteo.contaspese.presentation.components.template.SignInTemplate

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AuthScreen(
    navHostController: NavHostController,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val email = remember { authViewModel.email }
    val password = remember { authViewModel.password }
    val isLoggedIn = remember { authViewModel.isLoggedIn }
    val errorMessage = remember { authViewModel.errorMessage }
    val currentPage = remember { authViewModel.currentPage }

    if (isLoggedIn.value) {
        navHostController.navigate(Screens.Home.name)
    }

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = currentPage.value.toString()) },
        )
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            when (currentPage.value) {
                AuthScreenName.Login -> {
                    LoginTemplate(
                        email = email.value,
                        password = password.value,
                        onEmailChange = authViewModel::updateEmail,
                        onPasswordChange = authViewModel::updatePassword,
                        onSubmit = authViewModel::login,
                        onClickForgotPassword = { authViewModel.setCurrentPage(AuthScreenName.ForgotPassword) },
                        onClickSignIn = { authViewModel.setCurrentPage(AuthScreenName.SignIn) }
                    )
                }
                AuthScreenName.SignIn -> {
                    SignInTemplate(
                        email = email.value,
                        password = password.value,
                        onEmailChange = authViewModel::updateEmail,
                        onPasswordChange = authViewModel::updatePassword,
                        onSubmit = authViewModel::signIn,
                        onClickTextButton = { authViewModel.setCurrentPage(AuthScreenName.Login) }
                    )
                }
                AuthScreenName.ForgotPassword -> {
                    ForgotPasswordTemplate(
                        email = email.value,
                        onUpdateEmail = authViewModel::updateEmail,
                        onClickResetPassword = {
                            authViewModel.resetPassword(email.value)
                            authViewModel.setCurrentPage(AuthScreenName.Login)
                        },
                        onClickLogin = { authViewModel.setCurrentPage(AuthScreenName.Login) }
                    )
                }
            }
        }
        if (errorMessage.value.isNotEmpty()) {
            Toast.makeText(LocalContext.current, errorMessage.value, Toast.LENGTH_SHORT).show()
        }
    }
}