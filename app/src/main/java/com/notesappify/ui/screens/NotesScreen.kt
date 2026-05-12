package com.notesappify.ui.screens

import androidx.compose.material.icons.filled.Add
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.notesappify.data.Notes
import com.notesappify.ui.components.AlertDialogUi
import com.notesappify.ui.components.CardUi
import com.notesappify.ui.viewmodel.NotesViewModel

@Composable
fun NotesScreens(
    notesViewModel: NotesViewModel = hiltViewModel(),
    addNotes: () -> Unit,
    update:(String) -> Unit
) {
    val allNotes = notesViewModel.getNotes.collectAsState(initial = listOf())

    var showDialog by rememberSaveable { mutableStateOf(false) }
    var currentNote by remember { mutableStateOf(Notes()) }

    Scaffold(
        floatingActionButton = {
            IconButton(onClick = addNotes) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = ""
                )
            }
        }
    ) {
        LazyColumn(
            modifier = Modifier.padding(it)
        ) {
            items(allNotes.value) { note ->
                CardUi(note, delete = {
                    showDialog = true
                    currentNote = note
                }, update = {
                    update(note.id.toString())
                })
            }
        }
        AlertDialogUi(
            showDialog,
            confirmButton = {
                notesViewModel.deleteNote(currentNote)
                showDialog = false
            }, dismissButton = {
                showDialog = false
            }
        )
    }
}