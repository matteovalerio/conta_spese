package it.matteo.contaspese.presentation.components.organism

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import it.matteo.contaspese.presentation.components.molecule.LabeledTextFieldMolecule

@ExperimentalComposeUiApi
@Composable
fun EmailPasswordOrganism(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onSubmit: () -> Unit,
    isLogin: Boolean,
    buttonEnabled: () -> Boolean
) {
    LabeledTextFieldMolecule(
        modifier = Modifier.padding(top = 30.dp),
        label = "Email",
        textFieldContent = email,
        onValueChange = onEmailChange,
        placeholder = "example@email.com",
    )
    LabeledPasswordTextFieldOrganism(
        label = "Password",
        textFieldValue = password,
        onValueChange = onPasswordChange,
        validationFunction = { password.isEmpty() || password.length < 6 },
        placeholder = "********"
    )

    if (!isLogin) {
        val confirmPassword = remember { mutableStateOf("") }

        LabeledPasswordTextFieldOrganism(
            label = "Confirm password",
            textFieldValue = confirmPassword.value,
            onValueChange = { confirmPassword.value = it },
            validationFunction = { confirmPassword.value != password },
            placeholder = "********"
        )
    }

    Button(
        modifier = Modifier
            .padding(top = 30.dp, start = 30.dp, end = 30.dp)
            .fillMaxWidth(),
        onClick = {
            if (email.isNotEmpty() && password.isNotEmpty()) {
                onSubmit()
            }
        },
        enabled = buttonEnabled()
    ) {
        Text(
            text = if (!isLogin) {
                "Sign-in"
            } else {
                "Login"
            }
        )
    }
}