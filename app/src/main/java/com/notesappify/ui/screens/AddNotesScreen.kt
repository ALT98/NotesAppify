package com.notesappify.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.notesappify.ui.components.ButtonUi
import com.notesappify.ui.components.TextFieldUi
import com.notesappify.ui.components.TextUi
import com.notesappify.ui.viewmodel.NotesViewModel

@Composable
fun AddNotesScreen(notesViewModel: NotesViewModel = viewModel()) {
    val notes by notesViewModel.notesValidation.collectAsState()


    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(5.dp)) {
        TextUi("Registrar Notas")
        TextFieldUi(notes.title, "Ingrese el titulo") { notesViewModel.setTitle(it) }
        TextFieldUi(
            notes.description,
            "Ingrese la descripcion"
        ) { notesViewModel.setDescription(it) }
        ButtonUi("Registrar", notesViewModel.enableButton()) { }
    }
}