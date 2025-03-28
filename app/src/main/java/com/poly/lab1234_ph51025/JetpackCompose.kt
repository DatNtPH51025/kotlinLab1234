package com.poly.lab1234_ph51025

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import com.poly.lab1234_ph51025.ui.theme.Lab1234_PH51025Theme

class JetpackCompose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent{
            NoteScreen()
        }
    }


}
@Composable
fun NoteScreen() {
    var notes by remember { mutableStateOf(listOf("Note 1", "Note 2", "Note 3")) }
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showDialog = true },
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add Note")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(8.dp)
        ) {
            notes.forEach { note ->
                NoteCard(noteText = note)
            }
        }
    }

    if (showDialog) {
        AddNoteDialog(
            onDismiss = { showDialog = false },
            onSave = { newNote ->
                if (newNote.isNotEmpty()) {
                    notes = notes + newNote
                }
                showDialog = false
            }
        )
    }
}

@Composable
fun NoteCard(noteText: String) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(color = Color.LightGray)
            .clickable { expanded = !expanded }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = noteText,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyLarge
                )
                if (expanded) {
                    Text(
                        text = "hehehehehehehehe...",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.DarkGray
                    )
                }
            }

            Icon(
                imageVector = if (expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                contentDescription = "Expand Note",
                modifier = Modifier.padding(start = 8.dp)
            )
        }
    }
}

@Composable
fun AddNoteDialog(onDismiss: () -> Unit, onSave: (String) -> Unit) {
    var textState by remember { mutableStateOf(TextFieldValue("")) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Thêm Ghi Chú") },
        text = {
            TextField(
                value = textState,
                onValueChange = { textState = it },
                placeholder = { Text("Nhập nội dung ghi chú...") }
            )
        },
        confirmButton = {
            Button(onClick = { onSave(textState.text) }) {
                Text("Lưu")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Hủy")
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
    Lab1234_PH51025Theme {
        NoteScreen()
    }
}