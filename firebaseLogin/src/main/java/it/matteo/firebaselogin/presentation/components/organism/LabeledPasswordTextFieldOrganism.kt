package it.matteo.contaspese.presentation.components.organism

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import it.matteo.contaspese.presentation.components.molecule.PasswordTextFieldMolecule

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LabeledPasswordTextFieldOrganism(
    modifier: Modifier = Modifier,
    label: String,
    textFieldValue: String,
    onValueChange: (String) -> Unit,
    validationFunction: () -> Boolean,
    placeholder: String
) {
    Column(
        modifier = modifier.padding(top = 30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = label)
        PasswordTextFieldMolecule(
            value = textFieldValue,
            onValueChange = onValueChange,
            validationFunction = { validationFunction() },
            placeholder = { Text(text = placeholder) }
        )
    }
}