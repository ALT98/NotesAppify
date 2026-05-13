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
import androidx.compose.ui.Alignment
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
fun AddNotesScreen(
    idNote: Int?,
    notesViewModel: NotesViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    val notes by notesViewModel.notesValidation.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(idNote) {
        if (idNote != null) {
            notesViewModel.setNote(idNote)
        }
    }

    LaunchedEffect(Unit) {
        notesViewModel.message.collect {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextUi(if (idNote != null) "Modificar Nota" else "Registrar Notas")
        TextFieldUi(notes.title, "Ingrese el titulo") { notesViewModel.setTitle(it) }
        TextFieldUi(
            notes.description,
            "Ingrese la descripcion"
        ) { notesViewModel.setDescription(it) }
        ButtonUi(
            if (idNote != null) "Modificar Nota" else "Registrar",
            notesViewModel.enableButton()
        ) {
            if (idNote != null) {
                notesViewModel.updateNote()
            } else {
                notesViewModel.insertNote()
            }
            onBack()
        }
    }
}