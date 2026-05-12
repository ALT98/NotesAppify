package com.notesappify.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.filled.Add
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun NotesScreens(addNotes:() -> Unit) {
    Scaffold(
        floatingActionButton = {
            IconButton(onClick = addNotes) {
                Icon(imageVector = Icons.Default.Add,
                    contentDescription = "")
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {

        }
    }
}