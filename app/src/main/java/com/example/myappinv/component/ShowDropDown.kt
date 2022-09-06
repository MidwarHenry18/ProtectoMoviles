package com.example.myappinv


import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

@Composable
fun showDropDown() {
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current
    Box(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.TopEnd)) {
        IconButton(onClick = { expanded = true }) {
            Icon(Icons.Default.MoreVert, contentDescription = "Settings")
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(onClick = {
                Toast.makeText(context,"Configuración",Toast.LENGTH_SHORT).show()
            }) {
                Text("Add")
            }
            DropdownMenuItem(onClick = {
                Toast.makeText(context,"Acerca de",Toast.LENGTH_SHORT).show()
            }) {
                Text("Remove")
            }
            Divider()
            DropdownMenuItem(onClick = {
                Toast.makeText(context,"Cerrar Sesión",Toast.LENGTH_SHORT).show()
            }) {
                Text("Clear")
            }
        }
    }
}