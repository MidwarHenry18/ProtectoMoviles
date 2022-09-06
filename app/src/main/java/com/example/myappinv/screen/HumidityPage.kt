package com.example.myappinv.screen

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PowerSettingsNew
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myappinv.Conection.getHumedSValue
import com.example.myappinv.R
import com.example.myappinv.component.CustomComponent
import com.example.myappinv.component.TextHeaderTitle
import com.example.myappinv.ui.theme.*
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


@Composable
fun Humedad_screen(navController: NavController, navInit: NavController){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background)
    ){
        HeaderHumedUI(navController,navInit)
        BodyHumedUI()


    }
}
@Composable
fun HeaderHumedUI(navController: NavController, navInit: NavController){
    TextHeaderTitle("Control Humedad ", "", navController, navInit)
}

@Composable
fun BodyHumedUI() {

    var myRef: DatabaseReference = FirebaseDatabase.getInstance().reference

    val scrollState = rememberScrollState()
    var show by rememberSaveable {
        mutableStateOf(false)
    }
    var tintButton by rememberSaveable {
        mutableStateOf(false)
    }
    var humidityAValue by rememberSaveable {
        mutableStateOf(58)
    }
    var humiditySValue by rememberSaveable {
        mutableStateOf(58)
    }


    var humededA = "57"//getHumedSValue("HumedadAmbiente")

    humidityAValue = humededA.toInt()

    var humededS = getHumedSValue("HumedadSuelo")

    //humiditySValue = humededS as Int

    var StateRiegOn = "1"
    var StateRiegOff = "0"


    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CustomComponent(
            maxIndicatorValue = 100,
            indicatorValue = humededS.toInt(),
            smallText = "Humedad",
            bigTextSuffix = "%",
            rangeInit = "0",
            rangeFinal = "100",
            StateValie = true
        )
        Surface(
            elevation = 6.dp,
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .padding(horizontal = 90.dp)
                .background(MaterialTheme.colors.background)
        ){

        }
        Spacer(modifier = Modifier.height(40.dp))
        Button(
            onClick = { tintButton = !tintButton },
            shape = RoundedCornerShape(45.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = if (tintButton) MaterialTheme.colors.surface else Color.White
            ),
            border = BorderStroke(1.dp, Color.LightGray),
            modifier = Modifier
                .width(90.dp)
                .height(90.dp)
                .background(MaterialTheme.colors.background)

        ) {
            Icon(
                imageVector = Icons.Filled.PowerSettingsNew,
                contentDescription = "",
                modifier = Modifier
                    .size(90.dp),
                tint = if (tintButton) Color.Green else Color.Red
            )

        }
        if (tintButton){
            //database.child("UsersData/25rzPXapucOtBgK0SgbNpAMZ1GE3/12345678/State/SIS_IRR").setValue(StateRiegOn.toInt())
            myRef.child("/UsersData/-N5ltsvqedvxYUSP8k1h/products/-N5m9I02pmsdrHPdtD1y/SensoresEstado/BombaRiego").setValue(StateRiegOn)
        }else{
            myRef.child("/UsersData/-N5ltsvqedvxYUSP8k1h/products/-N5m9I02pmsdrHPdtD1y/SensoresEstado/BombaRiego").setValue(StateRiegOff)
        }

        /*
        enableDialog(
            false,
            {show=false},
            {tintButton = true
            show=false}
        )
        */
        Row(
            modifier = Modifier.padding(start = 0.dp,top=8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){

            Text(
                text = "Bomba de Riego",
                fontFamily = PoppinsM,
                fontSize = 10.sp,
                color = MaterialTheme.colors.onBackground,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(start = 6.dp)
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        //StatisticProgressUI(humeded.toString().toFloat(),75f)
        //Spacer(modifier = Modifier.height(10.dp))


        //Spacer(modifier = Modifier.height(10.dp))
        ViewDataDevicesUI(humidityAValue.toString() )

        Spacer(modifier = Modifier.height(200.dp))

    }
}

@Composable
fun ViewDataDevicesUI(s1: String) {
    Surface(
        elevation = 6.dp,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 5.dp)
            .background(MaterialTheme.colors.background)
    ){
        Box(
            Modifier
                .clip(shape = RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .background(MaterialTheme.colors.secondary),
            Alignment.Center
        ) {
            Row(
                Modifier.padding(start = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Column(
                    modifier = Modifier.width(200.dp)
                ) {
                    Text(
                        text = "Humedad del Ambiente",
                        fontFamily = PoppinsR,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.onBackground
                    )
                    Text(
                        text = "Humedad relativa interna",
                        fontFamily = PoppinsR,
                        fontSize = 14.sp,
                        color = MaterialTheme.colors.onBackground
                    )
                }
                Spacer(modifier = Modifier.width(6.dp))
                GraficBarProgress(s1)
            }

        }


    }

}

@Composable
fun GraficBarProgress(data:String) {
    Box(
        modifier = Modifier
            .width(120.dp)
            .padding(19.dp),
        contentAlignment = Alignment.Center
    ){
        Canvas(
            modifier = Modifier.size(80.dp)
        ){
            drawCircle(
                SolidColor(Color(0xFFBCBDBE)),
                size.width/2,
                style = Stroke(20f)
            )

            val convertedPrimaryValue = (data.toFloat()/100)*360
            val convertedSecondaryValue = ((75f/100)*360)



            drawArc(
                brush = SolidColor(Purple500),
                startAngle = -90f,
                sweepAngle = convertedPrimaryValue,
                useCenter = false,
                style = Stroke(20f, cap = StrokeCap.Round)
            )
        }
        Text(
            text = "$data %",
            fontFamily = PoppinsR,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onBackground
        )

    }
}


@Composable
fun StatisticProgressUI(primaryProgress: Float, secondaryProgress:Float){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ){
        Canvas(
            modifier = Modifier.size(180.dp)
        ){
            drawCircle(
                SolidColor(Color(0xFFBCBDBE)),
                size.width/2,
                style = Stroke(50f)
            )

            val convertedPrimaryValue = (primaryProgress/100)*360
            val convertedSecondaryValue = ((secondaryProgress/100)*360)

            drawArc(
                brush = SolidColor(backgroundPrimary),
                startAngle = -90f,
                sweepAngle = convertedSecondaryValue,
                useCenter = false,
                style = Stroke(45f, cap = StrokeCap.Round)
            )

            drawArc(
                brush = SolidColor(Purple500 ),
                startAngle = -90f,
                sweepAngle = convertedPrimaryValue,
                useCenter = false,
                style = Stroke(45f, cap = StrokeCap.Round)
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_hum1),
                contentDescription = "",
                modifier = Modifier
                    .size(60.dp),
                tint = MaterialTheme.colors.onSurface
            )
            Text(
                text = "$primaryProgress %",
                fontFamily = PoppinsR,
                fontSize = 28.sp,
                color = MaterialTheme.colors.onSurface,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

        }

    }

}
