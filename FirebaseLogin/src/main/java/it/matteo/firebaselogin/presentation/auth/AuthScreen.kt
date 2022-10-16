package it.matteo.firebaselogin.presentation.auth

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import it.matteo.contaspese.presentation.components.molecule.Loader
import it.matteo.contaspese.presentation.components.template.ForgotPasswordTemplate
import it.matteo.contaspese.presentation.components.template.LoginTemplate
import it.matteo.contaspese.presentation.components.template.SignInTemplate

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AuthScreen(
    onLogin: () -> Unit,
    authViewModel: AuthViewModel = hiltViewModel(),

    ) {
    val email = remember { authViewModel.email }
    val password = remember { authViewModel.password }
    val name = remember { authViewModel.name }
    val errorMessage = remember { authViewModel.errorMessage }
    val currentPage = remember { authViewModel.currentPage }
    val state = remember { authViewModel.state }
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    var toast: Toast? = null

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
                        onSubmit = {
                            keyboardController?.hide()
                            authViewModel.login()
                        },
                        onClickForgotPassword = { authViewModel.updateCurrentPage(AuthScreenName.ForgotPassword) },
                        onClickSignIn = { authViewModel.updateCurrentPage(AuthScreenName.SignIn) }
                    )
                }
                AuthScreenName.SignIn -> {
                    SignInTemplate(
                        email = email.value,
                        password = password.value,
                        name = name.value,
                        onEmailChange = authViewModel::updateEmail,
                        onPasswordChange = authViewModel::updatePassword,
                        onNameChange = authViewModel::updateName,
                        onSubmit = authViewModel::signIn,
                        onClickTextButton = { authViewModel.updateCurrentPage(AuthScreenName.Login) }
                    )
                }
                AuthScreenName.ForgotPassword -> {
                    ForgotPasswordTemplate(
                        email = email.value,
                        onUpdateEmail = authViewModel::updateEmail,
                        onClickResetPassword = {
                            authViewModel.resetPassword(email.value)
                            Toast.makeText(
                                context,
                                "Email sent! Please check also in your spam folder.",
                                Toast.LENGTH_SHORT
                            ).show()
                            authViewModel.updateCurrentPage(AuthScreenName.Login)
                        },
                        onClickLogin = { authViewModel.updateCurrentPage(AuthScreenName.Login) }
                    )
                }
            }
        }
        if (state.value == AuthState.Loading) {
            Loader()
        }
        if (state.value == AuthState.Error) {
            toast?.cancel()
            toast = Toast.makeText(context, errorMessage.value, Toast.LENGTH_SHORT)
            toast?.show()
            authViewModel.updateState(AuthState.Idle)
        }
        if (state.value == AuthState.Logged) {
            authViewModel.updateState(AuthState.Idle)
            onLogin()
        }
    }
}