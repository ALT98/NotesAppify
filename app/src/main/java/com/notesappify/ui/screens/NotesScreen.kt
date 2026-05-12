package com.notesappify.ui.screens

import androidx.compose.material.icons.filled.Add
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.notesappify.ui.components.CardUi
import com.notesappify.ui.viewmodel.NotesViewModel

@Composable
fun NotesScreens(
    notesViewModel: NotesViewModel = hiltViewModel(),
    addNotes: () -> Unit
) {
    val allNotes = notesViewModel.getNotes.collectAsState(initial = listOf())
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
            items(allNotes.value) {
                CardUi(it, delete = {}, update = {})
            }
        }
    }
}