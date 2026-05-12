package com.notesappify.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.notesappify.ui.components.ButtonUi
import com.notesappify.ui.components.TextFieldUi
import com.notesappify.ui.components.TextUi
import com.notesappify.ui.viewmodel.NotesViewModel

@Composable
fun AddNotesScreen(notesViewModel: NotesViewModel = hiltViewModel()) {
    val notes by notesViewModel.notesValidation.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        notesViewModel.message.collect {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        TextUi("Registrar Notas")
        TextFieldUi(notes.title, "Ingrese el titulo") { notesViewModel.setTitle(it) }
        TextFieldUi(
            notes.description,
            "Ingrese la descripcion"
        ) { notesViewModel.setDescription(it) }
        ButtonUi("Registrar", notesViewModel.enableButton()) {
            notesViewModel.insertNote()
        }
    }
}