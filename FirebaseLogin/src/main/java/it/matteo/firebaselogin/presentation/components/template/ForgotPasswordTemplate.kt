package it.matteo.contaspese.presentation.components.template

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import it.matteo.contaspese.presentation.components.molecule.LabeledTextFieldMolecule

@Composable
fun ForgotPasswordTemplate(
    email: String,
    onUpdateEmail: (String) -> Unit,
    onClickResetPassword: () -> Unit,
    onClickLogin: () -> Unit
) {
    Column(
        modifier = Modifier.padding(top = 30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LabeledTextFieldMolecule(
            label = "Email",
            textFieldContent = email,
            onValueChange = onUpdateEmail,
            placeholder = "example@email.com"
        )
        Button(
            modifier = Modifier
                .padding(top = 30.dp, start = 30.dp, end = 30.dp)
                .fillMaxWidth(),
            onClick = onClickResetPassword
        ) {
            Text(text = "Reset password")
        }
        Divider(modifier = Modifier.padding(top = 30.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Already an user?")
            TextButton(onClick = onClickLogin) {
                Text(text = "Login")
            }
        }
    }
}
