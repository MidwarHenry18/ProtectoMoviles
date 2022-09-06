package com.example.myappinv.screen


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PowerSettingsNew
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myappinv.Conection.getValueTemperature
import com.example.myappinv.R
import com.example.myappinv.component.CustomComponent
import com.example.myappinv.component.TextHeaderTitle
import com.example.myappinv.ui.theme.PoppinsM
import com.example.myappinv.ui.theme.PoppinsR
import com.example.myappinv.ui.theme.Purple500
import com.example.myappinv.ui.theme.backgroundPrimary
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

private lateinit var database: DatabaseReference
@Composable
fun Control_screen(navController: NavController, navInit: NavController){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background)
    ){
        HeaderControl(navController,navInit)
        BodyControl()

    }
}
@Composable
fun HeaderControl(navController: NavController, navInit: NavController) {
    TextHeaderTitle("Control de Temperatura", "Monitoreo  de Temperatura", navController, navInit)
}

@Composable
fun BodyControl() {
    val scrollState = rememberScrollState()
    //var temperatureVar = getHumedSValue(s = "AT_ASIG")
    var temp = getValueTemperature()

    var variadorTemp = rememberSaveable {
        mutableStateOf(56)
    }

    var buttonActivec = rememberSaveable {
        mutableStateOf(false)
    }

    var buttonActivev = rememberSaveable {
        mutableStateOf(false)
    }

    var temperatureValueControl = remember {
        mutableStateOf(25)
    }
    var temperatureValueRead = remember {
        mutableStateOf(25)
    }
    var contador = remember { mutableStateOf(1) }
    temperatureValueRead.value = temp.toInt()

    //temperatureValueControl.value = temperatureVar.toString()

    var myRefc: DatabaseReference = FirebaseDatabase.getInstance().reference

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        //PruebaDeLectura(temperatureValueRead,temperatureValueControl)

        CustomComponent(
            maxIndicatorValue = 120,
            indicatorValue = temperatureValueRead.value,
            smallText = "Temperatura",
            bigTextSuffix = "°C",
            rangeInit = "-40",
            rangeFinal = "80"
        )
        //ProgressBarUI(progress=43f,temperature.toString(), "ºC")

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 60.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            buttomOfControlee(
                buttonActivec,
                painterResource(id = R.drawable.ic_temp5),
                "Calefactor",
            )
            //Spacer(modifier = Modifier.width(10.dp))
            var TempF= temperatureValueRead.value.toFloat() * 9/5 + 32
            buttomOfControlee(
                buttonActivev,
                painterResource(id = R.drawable.ic_temp5),
                "Ventilador",
            )

        }

        if (buttonActivec.value){
            myRefc.child("UsersData/-N5ltsvqedvxYUSP8k1h/products/-N5m9I02pmsdrHPdtD1y/SensoresEstado/Calefactor").setValue("1")
        }else{
            myRefc.child("UsersData/-N5ltsvqedvxYUSP8k1h/products/-N5m9I02pmsdrHPdtD1y/SensoresEstado/Calefactor").setValue("0")
        }

        if (buttonActivev.value){
            myRefc.child("UsersData/-N5ltsvqedvxYUSP8k1h/products/-N5m9I02pmsdrHPdtD1y/SensoresEstado/Ventilador").setValue("1")
        }else{
            myRefc.child("UsersData/-N5ltsvqedvxYUSP8k1h/products/-N5m9I02pmsdrHPdtD1y/SensoresEstado/Ventilador").setValue("0")
        }

        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.padding(start = 0.dp,top=20.dp),
            horizontalArrangement = Arrangement.Center
        ){
            Image(
                painter = painterResource(id = R.drawable.ic_vineta),
                contentDescription = "",
                modifier = Modifier
                    .size(25.dp)
            )

            Text(
                text = "Variar temperatura",
                fontFamily = PoppinsM,
                fontSize = 18.sp,
                color = MaterialTheme.colors.onBackground,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 6.dp)
            )
        }
        Spacer(modifier = Modifier.height(5.dp))
        ProgressBarIndicator(temperatureValueControl)
        Spacer(modifier = Modifier.height(10.dp))
        ButtonsOfControl(temperatureValueControl,variadorTemp)
        //contentInformation()

    }
}
@Composable
fun buttomOfControlee(buttonActive: MutableState<Boolean>, painterResource: Painter, tipo: String) {

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {
        Button(
            onClick = { buttonActive.value = !buttonActive.value },
            shape = RoundedCornerShape(45.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (buttonActive.value) MaterialTheme.colors.surface else Color.White
            ),
            border = BorderStroke(1.dp, Color.LightGray),
            modifier = Modifier
                .width(90.dp)
                .height(90.dp)
                .background(MaterialTheme.colors.background)
                .clip(shape = RoundedCornerShape(8.dp))

        ) {
            Icon(
                imageVector = Icons.Filled.PowerSettingsNew,
                contentDescription = "",
                modifier = Modifier
                    .size(90.dp),
                tint = if (buttonActive.value) Color.Green else Color.Red
            )

        }
        Spacer(modifier = Modifier.height(3.dp))
        Text(
            text = tipo,
            fontFamily = PoppinsR,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onBackground,
            textAlign = TextAlign.Center
        )
    }

}

@Composable
fun PruebaDeLectura(temperatureValueRead: MutableState<Int>, temperatureValueControl: MutableState<Int>) {
    var contador = remember {
        mutableStateOf(1)
    }
    if (contador.value == 10){
        if (temperatureValueControl.value > temperatureValueRead.value){

            temperatureValueRead.value++
        }

        if (temperatureValueControl.value < temperatureValueRead.value){
            temperatureValueRead.value--
        }
        if (temperatureValueControl.value == temperatureValueRead.value){

        }
        contador.value=1
    }
    contador.value++

}

@Composable
fun ProgressBarIndicator(temperatureValueControl: MutableState<Int>) {
    //var progress by remember { mutableStateOf(temperatureValueControl.value/100) }
    var progress = (temperatureValueControl.value-18)/10F
    val animatedProgress by animateFloatAsState(
        targetValue = progress,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    )

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Spacer(Modifier.height(5.dp))

        LinearProgressIndicator(
            progress = animatedProgress,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 35.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .wrapContentHeight()
                .height(25.dp),
            color = MaterialTheme.colors.primaryVariant
        )

    }


}

@Composable
fun ButtonsOfControl(temperatureValueControl: MutableState<Int>, variadorTemp: MutableState<Int>) {
    var show = rememberSaveable {
        mutableStateOf(false)
    }
    var tintButton = rememberSaveable {
        mutableStateOf(false)
    }

    Row(modifier = Modifier
        .padding(horizontal = 5.dp)) {
        ButtomMore("-",temperatureValueControl,tintButton)

        measureTemperature(
            temperatureValueControl.value.toString(),
            "18°C a 28°C",
            " °C",
            Modifier.padding(5.dp),
            show,
            tintButton
        )
        ButtomMore("+", temperatureValueControl, tintButton)

    }
    if (tintButton.value ){
        enableDialog(
            show,
            {show.value=true},
            {tintButton.value = false
                show.value=true}
        )
    }else{
        enableDialog(
            show,
            {show.value=false},
            {tintButton.value = true
                show.value=false}
        )
    }

}

@Composable
fun ButtomMore(
    text: String,
    temperatureValueControl: MutableState<Int>,
    tintButton: MutableState<Boolean>
) {
    var myReft: DatabaseReference = FirebaseDatabase.getInstance().reference

    Button(
        onClick = {
            if (tintButton.value){
                if (temperatureValueControl.value in 18..28){
                    if (text == "+" && temperatureValueControl.value <28){
                        temperatureValueControl.value += 1
                        myReft.child("UsersData/-N5ltsvqedvxYUSP8k1h/products/-N5m9I02pmsdrHPdtD1y/Lecturas/ValorAsignado").setValue(temperatureValueControl.value)
                    }
                    if (text == "-" && temperatureValueControl.value >18){
                        temperatureValueControl.value -= 1
                        myReft.child("UsersData/-N5ltsvqedvxYUSP8k1h/products/-N5m9I02pmsdrHPdtD1y/Lecturas/ValorAsignado").setValue(temperatureValueControl.value)
                    }

                }
            }
        },
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.secondary
        ),
        border = BorderStroke(1.dp, Color.LightGray),
        modifier = Modifier
            .width(75.dp)
            .height(75.dp)
            .background(Color.White)
            .clip(shape = RoundedCornerShape(8.dp))

    ) {
        Text(
            text = text,
            color = backgroundPrimary,
            fontSize = 28.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier.padding(10.dp)
        )

    }

}


@Composable
fun measureTemperature(
    temperature: String,
    rank: String,
    type: String,
    padding: Modifier,
    show: MutableState<Boolean>,
    tintButton: MutableState<Boolean>
) {
    Box(
        Modifier
            .width(160.dp)
            .height(75.dp)
            .background(MaterialTheme.colors.secondary)
            .padding(horizontal = 8.dp)
            .clip(shape = RoundedCornerShape(10.dp))
            .border(border = BorderStroke(1.dp, Color.LightGray))
            .clickable {
                show.value = true
            },
        Alignment.Center
    ) {

        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){

            Text(
                text = "$temperature $type",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Purple500
            )
            Text(
                text = "$rank ",
                fontSize = 14.sp,
                color = MaterialTheme.colors.onBackground
            )
        }
    }


}

@Composable
fun ProgressBarUI(progress:Float,Temperatura:String,type: String){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .size(170.dp)
                .padding(6.dp)
        ){
            drawCircle(
                SolidColor(Color(0xFFD5D5D5)),
                size.width/2,
                style = Stroke(45f)
            )

            val converted = (Temperatura.toFloat()/100)*360
            drawArc(
                brush = SolidColor(Purple500),
                startAngle = -90f,
                sweepAngle = converted,
                useCenter = false,
                style = Stroke(45f, cap = StrokeCap.Round)
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_temp2),
                contentDescription = "",
                modifier = Modifier
                    .size(60.dp),
                tint = MaterialTheme.colors.onSurface
            )
            Text(
                text = "$Temperatura $type",
                fontFamily = PoppinsR,
                fontSize = 30.sp,
                color = MaterialTheme.colors.onSurface,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )
        }
    }
}
@Composable
fun contentInformation() {
    Surface(
        elevation = 10.dp,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .padding(20.dp)
    ) {
        Box(
            Modifier
                .clip(shape = RoundedCornerShape(10.dp))
                .background(MaterialTheme.colors.secondary)
        ) {
            Column {
                Text(
                    text = "Sugerencias",
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onBackground
                )

                ItemSugerencias(
                    "Duarnte en el día",
                    "La temperatura mínima requerida para las plantas es de 10-15°C, mientras que 30°C como temperatura máxima",
                    painterResource(id = R.drawable.ic_check2)
                )
                Spacer(modifier = Modifier.height(5.dp))
                ItemSugerencias(
                    "Duarante la noche",
                    "Las termperaturas diurnas y nocturnas deben ser entre de 5 – 7°C.",
                    painterResource(id = R.drawable.ic_check2)
                )
                Spacer(modifier = Modifier.height(10.dp))


            }

        }
    }
}

@Composable
fun ItemSugerencias(s1: String, s2: String, icon:Painter) {
    Row(
        Modifier.padding(horizontal = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon,
            contentDescription = "",
            modifier = Modifier
                .size(30.dp),
            tint = MaterialTheme.colors.onSurface

        )
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(
                text = s1,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colors.onBackground
            )
            Text(
                text = s2,
                fontSize = 13.sp,
                fontWeight = FontWeight.Normal,
                color =MaterialTheme.colors.onBackground
            )
        }
    }
}

@Composable
fun enableDialog(
    show:MutableState<Boolean>,
    onDismiss:() -> Unit,
    onCorfirm:() -> Unit

){
    if (show.value)
        AlertDialog(
            onDismissRequest = {},
            confirmButton = { TextButton(onClick = {onCorfirm()}) {
                Text(text = "Aceptar", color = Color.White)
            }},
            dismissButton = { TextButton(onClick = {onDismiss()}) {
                Text(text = "Denegar", color = Color.White)
            }},
            title = { Text(text = "Confirmar")},
            text = { Text(text = "Desea modificar temperatura?")}
        )

}
