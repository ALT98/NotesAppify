package com.notesappify.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.NorthWest
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.notesappify.custom_fonts.InterFontFamily
import com.notesappify.data.models.Notes
import com.notesappify.data.models.Search
import com.notesappify.utils.timeAgo

@Composable
fun TextUi(text: String) {
    Text(text, fontSize = 20.sp, fontFamily = InterFontFamily, fontWeight = FontWeight.Bold)
}

@Composable
fun TextFieldSearch(
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    onDone: () -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color(0xFFF3F3F3),
            unfocusedContainerColor = Color(0xFFF3F3F3),
            disabledContainerColor = Color(0xFFF3F3F3),
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = Color(0xFF004C4C)
        ),
        textStyle = TextStyle(
            fontFamily = InterFontFamily, fontSize = 14.sp, fontWeight = FontWeight.Normal
        ),
        minLines = 1,
        maxLines = 1,
        shape = RoundedCornerShape(12.dp),
        placeholder = {
            Text(text = label)
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                onDone()
            }
        ))
}

@Composable
fun TextFieldUi(value: String, label: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        label = {
            Text(text = label)
        })
}

@Composable
fun MultilineTextFieldUi(value: String, label: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,
        maxLines = 15,
        minLines = 3,
        singleLine = false,
        onValueChange = onValueChange,
        modifier = Modifier.fillMaxWidth(),
        label = {
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
            .padding(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            /*Row {
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
            }*/

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = notes.title,
                    fontFamily = InterFontFamily,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF1a1c1c),
                    fontSize = 18.sp,
                    modifier = Modifier.weight(1f)
                )

                Text(
                    text = notes.timeAgo(),
                    fontFamily = InterFontFamily,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF3F4948),
                    maxLines = 1
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            if (notes.subtitle.isNotBlank()) {
                // Quoted text
                Text(
                    text = notes.subtitle,
                    fontFamily = InterFontFamily,
                    fontStyle = FontStyle.Italic,
                    color = Color(0xFF566060),
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(8.dp))
            }

            Text(
                text = notes.description,
                fontFamily = InterFontFamily,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF1a1c1c),
                fontSize = 12.sp
            )
        }
    }
}

@Composable
fun AlertDialogUi(showDialog: Boolean, confirmButton: () -> Unit, dismissButton: () -> Unit) {
    if (showDialog) {
        AlertDialog(title = {
            Text("Estás seguro que deseas eliminar?")
        }, onDismissRequest = dismissButton, confirmButton = {
            TextButton(onClick = confirmButton) {
                Text("Confirmar")
            }
        }, dismissButton = {
            TextButton(onClick = dismissButton) {
                Text("Cancelar")
            }
        })
    }
}

@Composable
fun LatestSearches(value: List<Search>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 0.dp)
    ) {
        Spacer(modifier = Modifier.height(25.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Recent Searches",
                fontFamily = InterFontFamily,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF004C4C),
                fontSize = 22.sp,
                modifier = Modifier.weight(1f)
            )

            Text(
                text = "Clear All",
                fontFamily = InterFontFamily,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                color = Color(0xFF3F4948),
                maxLines = 1
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            value.forEach { search ->
                SearchedItem(search)
            }
        }
    }
}

@Composable
fun SearchedItem(search: Search) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {}
        ) {
            Icon(
                imageVector = Icons.Default.History,
                contentDescription = "Back",
                tint = Color.Gray
            )
        }

        Text(
            text = search.param,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.Start
        )

        IconButton(
            onClick = { }
        ) {
            Icon(
                imageVector = Icons.Default.NorthWest,
                contentDescription = "Menu",
                tint = Color.LightGray
            )
        }
    }
}

@Composable
fun NotesCoincidenceContainer(coincidences: List<Notes>) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        coincidences.forEach { note ->
            CardUi(note, delete = {}, update = {})
        }
    }
}