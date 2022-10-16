package it.matteo.contaspese.presentation.components.molecule

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import it.matteo.contaspese.presentation.components.atom.TextFieldAtom

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LabeledTextFieldMolecule(
    modifier: Modifier = Modifier,
    label: String,
    textFieldContent: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = label)
        TextFieldAtom(
            value = textFieldContent,
            onValueChange = onValueChange,
            placeholder = { Text(text = placeholder) })
    }
}