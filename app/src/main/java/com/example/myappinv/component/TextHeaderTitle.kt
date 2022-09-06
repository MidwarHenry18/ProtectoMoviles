package com.example.myappinv.component

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myappinv.R
import com.example.myappinv.ui.theme.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.window.isPopupLayout
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.myappinv.model.menuItem

@Composable
fun TextHeaderTitle(
    title: String,
    subTitle: String,
    navController: NavController,
    navInit: NavController
){
    val context = LocalContext.current
    val expanded = remember { mutableStateOf(false) }
    val menuItems = listOf("Ajustes", "Cerrar Sesión")
    val scaffoldState = rememberScaffoldState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
            .height(60.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(start = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = title,
                fontFamily = PoppinsM,
                fontSize = 18.sp,
                color = MaterialTheme.colors.onBackground,
                fontWeight = FontWeight.Bold
            )
        }

        Row {
            Icon(
                painter = painterResource(id = R.drawable.ic_mnt),
                contentDescription = "",
                modifier = Modifier
                    .size(35.dp).clickable{expanded.value = true},
                tint = MaterialTheme.colors.onBackground.copy(alpha = 0.5f)

            )
            Spacer(modifier = Modifier.width(10.dp))
        }

        DropdownMenu(
            expanded = expanded.value,
            offset = DpOffset((-40).dp, (-40).dp),
            onDismissRequest = { expanded.value = false }
        ) {
            menuItems.forEach {
                DropdownMenuItem(onClick = {

                    if(it == "Cerrar Sesión"){
                        val save: SharedPreferences = context.getSharedPreferences("midwar", Context.MODE_PRIVATE)
                        val editor = save.edit()
                        editor.clear()
                        editor.apply()

                        navController.popBackStack()
                        navInit.popBackStack()
                        navInit.navigate("login_page")
                    }

                    if(it == "Ajustes"){
                        navController.navigate("setting_page")
                    }

                    expanded.value = false
                }) {
                    Text(text = it)
                }
            }
        }
    }


}