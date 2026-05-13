package com.notesappify.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.notesappify.data.Notes

@Composable
fun TextUi(text: String) {
    Text(text, fontSize = 20.sp, fontFamily = FontFamily.Monospace)
}

@Composable
fun TextFieldUi(value: String, label: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value, onValueChange = onValueChange, modifier = Modifier.fillMaxWidth(), label = {
            Text(text = label)
        })
}

@Composable
fun ButtonUi(text: String, enabled: Boolean, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Blue
        )
    ) {
        Text(text = text, color = Color.White, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun CardUi(notes: Notes, delete: () -> Unit, update: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(18.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        shape = RoundedCornerShape(25.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Row {
                IconButton(onClick = update) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "")
                }
                IconButton(onClick = delete) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "",
                        tint = Color.Red
                    )
                }
            }
            Text(notes.title)
            Text(notes.description)
        }
    }
}

@Composable
fun AlertDialogUi(showDialog: Boolean, confirmButton: () -> Unit, dismissButton: () -> Unit) {
    if(showDialog){
        AlertDialog(
            title = {
                Text("Estás seguro que deseas eliminar?")
            },
            onDismissRequest = dismissButton,
            confirmButton = {
                TextButton(onClick = confirmButton) {
                    Text("Confirmar")
                }
            },
            dismissButton = {
                TextButton(onClick = dismissButton) {
                    Text("Cancelar")
                }
            }
        )
    }
}