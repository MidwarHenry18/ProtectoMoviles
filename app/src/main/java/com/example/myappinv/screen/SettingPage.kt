package com.example.myappinv.screen

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.example.myappinv.R
import com.example.myappinv.ui.theme.PoppinsM
import com.example.myappinv.ui.theme.backgroundPrimary
import kotlinx.coroutines.launch

@Composable
fun Setting_screen(navController: NavController,navController1:NavController){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background)
    ){
        HeaderSUI()
        UserCardSUI()
        OptionsSHUI(
            navController,
            navController1,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .padding(horizontal = 10.dp, vertical = 10.dp)

        )

    }
}

@Composable
fun HeaderSUI(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(top = 15.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = "Ajustes",
            fontFamily = PoppinsM,
            fontSize = 24.sp,
            color = backgroundPrimary,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun UserCardSUI(){
    val context = LocalContext.current
    val save: SharedPreferences = context.getSharedPreferences("midwar", Context.MODE_PRIVATE)
    // a coroutine scope
    val scope = rememberCoroutineScope()
    // we instantiate the saveEmail class
    //val dataStore = StoreUserEmail(context)
    Row(modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp)) {
        Surface(
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription =""
            )

        }
        val sharedValue = save.getString("id_key","login")
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text(sharedValue.toString(), fontWeight = FontWeight.Bold)
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text("Usuario activo", style = MaterialTheme.typography.body2)
            }
        }
    }
    dividerLine()
}



@Composable
fun OptionsSHUI(nv:NavController,navController: NavController,modifier: Modifier = Modifier){


    val context = LocalContext.current
    // a coroutine scope
    val scope = rememberCoroutineScope()
    // we instantiate the saveEmail class
    //val dataStore = StoreUserEmail(context)

    Column(
        modifier = Modifier
            .padding()
            .padding(horizontal = 20.dp)
            .padding(top = 20.dp)
    ) {

        Row(
            modifier,
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                painter = painterResource(id = R.drawable.ic_notf),
                contentDescription = "",
                tint = Color(0xFF3ABE14),
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            labelText("Notificaciones")
            Spacer(modifier = Modifier.width(125.dp))
            LabelledSwitchSetting()

        }

        Row(
            modifier,
            verticalAlignment = Alignment.CenterVertically

        ){
            Icon(
                painter = painterResource(id = R.drawable.ic_modo),
                contentDescription = "",
                tint = Color(0xFF3ABE14),
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            labelText("Modo Oscuro")
            Spacer(modifier = Modifier.width(130.dp))
            LabelledSwitchSetting()
        }

        val intent = Intent(Intent.ACTION_SENDTO)
        var addresses = listOf("asistenciatecnica@mail.com").toTypedArray()
        var subject = "Asistencia técnica"
        intent.setData(Uri.parse("mailto:")) // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses)
        intent.putExtra(Intent.EXTRA_SUBJECT, subject)
        val context = LocalContext.current
        Row(
            modifier.clickable {
                startActivity(context, intent, null)
            },
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                painter = painterResource(id = R.drawable.ic_mail),
                contentDescription = "",
                tint = Color(0xFF3ABE14),
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            labelText("Asistencia Técnica")
        }

        Row(
            modifier.clickable {
                nv.navigate("ifu_page")
            },
            verticalAlignment = Alignment.CenterVertically

        ){
            Icon(
                painter = painterResource(id = R.drawable.ic_inst),
                contentDescription = "",
                tint = Color(0xFF3ABE14),
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            labelText("Instrucciones de uso")
        }

        Row(
            modifier.clickable {
                nv.navigate("policy_page")
            },
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                painter = painterResource(id = R.drawable.ic_seg),
                contentDescription = "",
                tint = Color(0xFF3ABE14),
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            labelText("Politicas de Privacidad")

        }

        Row(
            modifier.clickable {
                navController.popBackStack()

                navController.navigate("login_page") {
                    popUpTo("scream_setting") {
                        inclusive = true
                    }
                }


            },
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                painter = painterResource(id = R.drawable.ic_cesion),
                contentDescription = "",
                tint = Color(0xFF3ABE14),
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            labelText("Cerrar Sesión")

        }
    }

    dividerLine()
    versionView()
}
@Composable
fun versionView() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(vertical = 15.dp)
        ,
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_jard),
            contentDescription = "", modifier = Modifier.size(500.dp))
        Text(
            text = "",
            fontFamily = PoppinsM,
            style = MaterialTheme.typography.subtitle1
        )

    }
}

@Composable
fun LabelledSwitchSetting() {

    val checkedState = remember { mutableStateOf(true) }
    val context = LocalContext.current
    var enable = true
    Switch(
        modifier = Modifier
            .scale(1f),
        checked = checkedState.value,
        onCheckedChange = { checkedState.value = it },
        colors = SwitchDefaults.colors(
            checkedThumbColor = colorResource(id = R.color.primaryGreen)
        )
    )
    if (checkedState.value){
        enable = true
    }else{
        if (enable){
            enable = false
            Toast
                .makeText(context, "DesHabilitado", Toast.LENGTH_SHORT)
                .show()
        }
    }


}

@Composable
fun labelText(text: String){
    Text(
        text = text,
        fontFamily = PoppinsM,
        style = MaterialTheme.typography.subtitle1
    )
}

@Composable
fun dividerLine(){
    Divider(
        color = colorResource(id = R.color.primaryGreen),
        modifier = Modifier
            .fillMaxWidth(),
        thickness = 1.dp
    )
}