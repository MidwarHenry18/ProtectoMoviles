package com.example.myappinv.screen

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.myappinv.Conection.getCodProduct
import com.example.myappinv.R
import com.example.myappinv.component.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun RegisterPage(navController: NavController){

    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = colorResource(id = R.color.primaryGreen),
            modifier = Modifier.background(colorResource(id = R.color.colorfb))) {
            Icon(imageVector = Icons.Default.ArrowBackIos,
                contentDescription = "Atrás",
                modifier = Modifier
                    .clickable { navController.popBackStack() }
                    .width(70.dp))
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Atrás", color = Color.White)
        }
    }, content = { screen_register(navController)})
}

@Composable
fun screen_register(navController: NavController){

    val scrollState = rememberScrollState()
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(20.dp)
        .wrapContentSize(Alignment.Center)
        .verticalScroll(state = scrollState)
    ) {

        var emailValue = remember { mutableStateOf("")}
        var passValue = remember { mutableStateOf("") }

        var codProduct = remember { mutableStateOf("") }
        var codActiv = remember { mutableStateOf("") }

        val isEmailValid by derivedStateOf {
            Patterns.EMAIL_ADDRESS.matcher(emailValue.value).matches()
        }

        val isPasswordValid by derivedStateOf {
            passValue.value.length > 7
        }

        var isPasswordVisible = remember {
            mutableStateOf( false)
        }

        TextTitleBase(text = "REGISTRATE")
        TextSubTitleBase("- Usuario -")
        Spacer(modifier = Modifier.padding(5.dp))
        TextFieldBase("Correo",emailValue,isEmailValid)
        TextFieldPassBase("Contraseña", passValue,isPasswordVisible,isPasswordValid)
        Spacer(modifier = Modifier.padding(10.dp))
        TextSubTitleBase("- Equipo -")
        TextFieldEquip("Código del producto",codProduct)
        Spacer(modifier = Modifier.padding(10.dp))
        ButtonOnClickRegister("Registrarse",emailValue,passValue,navController,codProduct)

        Spacer(modifier = Modifier.height(280.dp))

    }
}

@Composable
fun ButtonOnClickRegister(
    text: String,
    emailValue: MutableState<String>,
    passValue: MutableState<String>,
    navController: NavController,
    codProduct: MutableState<String>
) {
    val auth = Firebase.auth
    val context = LocalContext.current
    var listprodct = listOf(
        "123456789a",
        "123456789b",
        "123456789c",
        "123456789d",
        "123456789e",
        "123456789f",
        "123456789g",
        "123456789h",
        "123456789i"
    )

    var active = rememberSaveable {
        mutableStateOf(false)
    }
    //stateSave(active,codProduct.value)
    Button(
        onClick = {
            listprodct.forEach{
                if (it == codProduct.value){
                    active.value = true
                }
            }
            if(active.value){
                auth.createUserWithEmailAndPassword(emailValue.value,passValue.value)
                    .addOnCompleteListener{
                        if (it.isSuccessful) {
                            //
                            Toast.makeText(context, "Registro exitoso...",
                                Toast.LENGTH_SHORT).show()
                            navController.navigate("login_page"){
                                popUpTo("register_page"){
                                    inclusive = true
                                }
                            }


                        } else {
                            //
                            Toast.makeText(context, "Sus datos de usuario son incorrectos, intente nuevamente!!",
                                Toast.LENGTH_SHORT).show()
                        }

                    }

            }else{
                Toast.makeText(context, "Sus datos son incorrectos!... ingrese nuevamente sus datos",
                    Toast.LENGTH_SHORT).show()
            }

        },
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(0.dp, 7.dp),
        colors = ButtonDefaults.textButtonColors(
            backgroundColor = colorResource(id = R.color.primaryGreen),
            contentColor = Color.White
        )
    ) {
        Text(text = text)
    }
}
@Composable
fun stateSave(active: MutableState<Boolean>, value: String) {
    val context = LocalContext.current
    var requesto by rememberSaveable {
        mutableStateOf("")
    }
    if (active.value) {
        var request = getCodProduct("value")
        Toast.makeText(
            context, "Mostrar el valor de  $request",
            Toast.LENGTH_SHORT
        ).show()

        if (request == "1") {
            Toast.makeText(
                context, "Prueba exitosa",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
