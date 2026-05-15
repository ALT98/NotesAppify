package com.notesappify.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.notesappify.custom_fonts.InterFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteDetailScreen() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Column(modifier = Modifier.fillMaxWidth()) {
                TopAppBar(
                    actions = {
                        IconButton(onClick = {}) {
                            Icon(Icons.Default.Edit, null)
                        }
                        IconButton(onClick = {}) {
                            Icon(Icons.Default.Share, null)
                        }
                        IconButton(onClick = {}) {
                            Icon(Icons.Default.Delete, null)
                        }
                    },
                    title = {

                    },
                    navigationIcon = {
                        IconButton(onClick = {}) {
                            Icon(Icons.AutoMirrored.Default.ArrowBack, null)
                        }
                    }
                )
                HorizontalDivider(
                    thickness = 0.5.dp,
                    color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                )
            }
        }
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            Text(
                fontFamily = InterFontFamily,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFF1A1C1C),
                fontSize = 18.sp,
                text = "Architectural Musings on Minimalist Design"
            )
            Text(
                fontFamily = InterFontFamily,
                fontStyle = FontStyle.Italic,
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFF566060),
                fontSize = 14.sp,
                text = "Reflections on the intersection of form and function in modern physical spaces."
            )
            Row(modifier = Modifier.fillMaxWidth()) {
                Icon(Icons.Default.CalendarToday, null)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    fontFamily = InterFontFamily,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.fillMaxWidth(),
                    color = Color(0xFF566060),
                    fontSize = 12.sp,
                    text = "Octuber 24, 2023"
                )
                
            }
        }
    }
}