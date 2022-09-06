package com.example.myappinv.component


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun TextFieldBase(text:String, textValue: MutableState<String>,isEmailValid:Boolean) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(0.dp, 5.dp),
        value = textValue.value,
        singleLine = true,
        onValueChange = {textValue.value = it},
        label = { Text(text = text)},
        placeholder = { Text(text = "Escriba su correo")},
        leadingIcon = { Icon(Icons.Filled.Email, null)},
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Email,
            imeAction = ImeAction.Next
        ),

        isError = isEmailValid,
        trailingIcon = {
            if (textValue.value.isNotBlank()){
                IconButton(onClick = { textValue.value = "" }) {
                    Icon(imageVector = Icons.Filled.Clear  , contentDescription = "")

                }
            }
        }
    )

}