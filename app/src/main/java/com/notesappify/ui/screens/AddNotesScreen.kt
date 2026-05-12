package com.notesappify.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.notesappify.ui.components.ButtonUi
import com.notesappify.ui.components.TextFieldUi
import com.notesappify.ui.components.TextUi

@Composable
fun AddNotesScreen() {
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(5.dp)) {
        TextUi("Registrar Notas")
        TextFieldUi("", "Ingrese el titulo") { }
        TextFieldUi("", "Ingrese la descripcion") { }
        ButtonUi("Registrar", true) { }
    }
}