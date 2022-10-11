package it.matteo.contaspese.presentation.components.template

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import it.matteo.contaspese.presentation.components.organism.EmailPasswordOrganism

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LoginTemplate(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSubmit: () -> Unit,
    onClickForgotPassword: () -> Unit,
    onClickSignIn: () -> Unit
) {
    EmailPasswordOrganism(
        email = email,
        password = password,
        onEmailChange = onEmailChange,
        onPasswordChange = onPasswordChange,
        onSubmit = onSubmit,
        isLogin = true
    )
    TextButton(onClick = onClickForgotPassword) {
        Text(text = "Forgot Password?")
    }
    Divider(modifier = Modifier.padding(top = 30.dp))
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Need an account?")
        TextButton(onClick = onClickSignIn) {
            Text(text = "Sign-in")
        }
    }
}