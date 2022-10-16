package it.matteo.contaspese.presentation.components.molecule

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.error
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import it.matteo.contaspese.presentation.components.atom.TextFieldAtom

@ExperimentalComposeUiApi
@Composable
fun PasswordTextFieldMolecule(
    value: String,
    onValueChange: (String) -> Unit,
    validationFunction: ((String) -> Boolean)? = null,
    placeholder: @Composable (() -> Unit)? = null
) {
    val isPasswordHidden = remember { mutableStateOf(true) }
    val isInErrorState = remember { mutableStateOf(false) }

    TextFieldAtom(
        value = value,
        onValueChange = {
            onValueChange(it)
            if (validationFunction != null) {
                isInErrorState.value = validationFunction(it)
            }
        },
        visualTransformation = if (isPasswordHidden.value) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        placeholder = placeholder,
        trailingIcon = {
            IconButton(onClick = {
                isPasswordHidden.value = !isPasswordHidden.value
            }) {
                val visibilityIcon = if (isPasswordHidden.value) {
                    Icons.Filled.Visibility
                } else {
                    Icons.Filled.VisibilityOff
                }
                val description = if (isPasswordHidden.value) "Show password" else "Hide password"
                Icon(imageVector = visibilityIcon, contentDescription = description)
            }
        },
        isError = isInErrorState.value,
        modifier = Modifier.semantics {
            if (isInErrorState.value) error("Password doesn't match.")
        }
    )
}