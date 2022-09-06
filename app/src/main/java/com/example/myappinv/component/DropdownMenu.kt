package com.example.myappinv.component


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun MyScreen() {
    Box(Modifier.fillMaxSize(), Alignment.Center) {

        Box() {
            var expanded by remember { mutableStateOf(false) }

            IconButton(onClick = { expanded = !expanded }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = null)
            }

            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                MyMenuItem("item 1")           // Icon visible
                MyMenuItem("item 2")           // Icon visible
                MyMenuItem("item 3 long text") // Icon width shrunk to 0
                MyMenuItem("item 4 long te")   // Icon visible but shrunk
            }

        }
    }
}
@Composable
fun MyMenuItem(text: String) {
    DropdownMenuItem(onClick = { }) {
        Text(text)
        Spacer(modifier = Modifier.weight(1f))
        Icon(imageVector = Icons.Default.Check, contentDescription = null) // <-- Icon
    }
}