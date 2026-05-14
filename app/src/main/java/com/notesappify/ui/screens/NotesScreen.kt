package com.notesappify.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material.icons.filled.Add
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.notesappify.custom_fonts.InterFontFamily
import com.notesappify.data.models.Notes
import com.notesappify.ui.components.AlertDialogUi
import com.notesappify.ui.components.CardUi
import com.notesappify.ui.viewmodel.NotesViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotesScreens(
    notesViewModel: NotesViewModel = hiltViewModel(), addNotes: () -> Unit, update: (String) -> Unit
) {
    val allNotes = notesViewModel.getNotes.collectAsState(initial = listOf())

    var showDialog by rememberSaveable { mutableStateOf(false) }
    var currentNote by remember { mutableStateOf(Notes()) }

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        notesViewModel.message.collect {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            Column(
                modifier = Modifier.fillMaxWidth()
                //.background(Color(0xFFFBF9F8))
            ) {
                TopAppBar(title = {
                    Text(
                        "Notes",
                        fontFamily = InterFontFamily,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                }, navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Menu, null)
                    }
                }, actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.Search, null)
                    }
                })
                HorizontalDivider(
                    thickness = 0.5.dp,
                    color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier.navigationBarsPadding(), onClick = addNotes
            ) {
                Icon(Icons.Default.Add, null)
            }
        }) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
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
        AlertDialogUi(showDialog, confirmButton = {
            notesViewModel.deleteNote(currentNote)
            showDialog = false
        }, dismissButton = {
            showDialog = false
        })
    }
}