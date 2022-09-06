package com.example.myappinv.screen


import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myappinv.Conection.getStateValue
import com.example.myappinv.R
import com.example.myappinv.component.TextHeaderTitle
import com.example.myappinv.ui.theme.PoppinsM
import com.example.myappinv.ui.theme.PoppinsR
import com.example.myappinv.ui.theme.backgroundPrimary
import com.example.myappinv.ui.theme.verde

@Composable
fun DivicesState_screen(navController: NavController, navInit: NavController){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background)
    ){
        HeaderStatesUI(navController,navInit)
        BodyStatesUI()
    }

}

@Composable
fun HeaderStatesUI(navController: NavController, navInit: NavController) {
    TextHeaderTitle("Control de Estados ", "", navController, navInit)
}

@Composable
fun BodyStatesUI() {
    val scrollState = rememberScrollState()
    Box(
        Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(10.dp))
            .background(MaterialTheme.colors.secondary)
            .verticalScroll(scrollState)

    ) {
        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){

            Image(
                painter = painterResource(id = R.drawable.ic_devis),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .size(195.dp)
                    .height(230.dp)
            )

            Row(
                modifier = Modifier.padding(start = 0.dp,top=20.dp),
                verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    painter = painterResource(id = R.drawable.ic_vineta),
                    contentDescription = "",
                    modifier = Modifier
                        .size(25.dp)
                )

                Text(
                    text = "Estado de los dispositivos",
                    fontFamily = PoppinsM,
                    fontSize = 18.sp,
                    color = MaterialTheme.colors.onBackground,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 6.dp)
                )
            }

            Spacer(modifier = Modifier.height(10.dp))
            var sensor_temperature = getStateValue(s = "Temperatura")
            StateDevicesObjet(
                "Temperatura",
                "Sensor DHP11",
                painterResource(id = R.drawable.ic_hum1),
                sensor_temperature
            )

            Spacer(modifier = Modifier.height(10.dp))
            var sensor_humidity = getStateValue(s = "HumedadSuelo")
            StateDevicesObjet(
                "Humedad",
                "Sensor FC-28",
                painterResource(id = R.drawable.ic_hum1),
                sensor_humidity
            )

            Spacer(modifier = Modifier.height(10.dp))
            var system_irrigation = getStateValue(s = "BombaRiego")
            StateDevicesObjet(
                "Riego ",
                "Sistema de riego",
                painterResource(id = R.drawable.ic_hum1),
                system_irrigation
            )
            Spacer(modifier = Modifier.height(10.dp))
            var system_calefactor = getStateValue(s = "Calefactor")
            StateDevicesObjet(
                "Calefactor ",
                "Incrementar temepratura",
                painterResource(id = R.drawable.ic_hum1),
                system_calefactor
            )
            Spacer(modifier = Modifier.height(10.dp))
            var system_ventilador = getStateValue(s = "Ventilador")
            StateDevicesObjet(
                "Ventilador",
                "Disminución de temperatura",
                painterResource(id = R.drawable.ic_hum1),
                system_ventilador
            )
            Spacer(modifier = Modifier.height(100.dp))

        }


    }
}



@Composable
fun StateDevicesObjet(text: String, des: String, icon: Painter, devices: Any) {
    Surface(
        elevation = 6.dp,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colors.background)
            .padding(horizontal = 20.dp)
    ) {
        Box(
            Modifier
                .clip(shape = RoundedCornerShape(10.dp))
                .background(MaterialTheme.colors.secondary)
        ) {

            Row(
                Modifier.padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){

                Column(
                    modifier = Modifier.width(200.dp)
                ) {
                    Text(
                        text = text,
                        fontFamily = PoppinsM,
                        fontSize = 18.sp,
                        color = MaterialTheme.colors.onBackground
                    )
                    Text(
                        text = des,
                        fontFamily = PoppinsR,
                        fontSize = 12.sp,
                        color = MaterialTheme.colors.onBackground
                    )
                }
                Spacer(modifier = Modifier.width(5.dp))
                Box(
                    Modifier
                        .size(110.dp, 40.dp)
                        .clip(RoundedCornerShape(50))
                        .background(if(devices.toString() == "0") Color.White else MaterialTheme.colors.primaryVariant)
                        .border(
                            width = 1.dp,
                            color = backgroundPrimary,
                            shape = RoundedCornerShape(25.dp)
                        )

                ){
                    var statesystem3:Int = 2 //getValueState("EstadoST")
                    ButtonState(statesystem3.toString().toInt(), devices)
                }

            }
        }
    }

}
@Composable
fun ButtonState(toInt: Int, devices: Any) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        if (devices.toString() == "1"){
            Text(
                modifier = Modifier.padding(start = 20.dp, top = 10.dp, bottom = 10.dp),
                text = "ON",
                fontFamily = PoppinsR,
                fontSize = 15.sp,
                color = Color.White,
                fontWeight = FontWeight.Medium
            )
            Box(
                Modifier
                    .padding(4.dp)
                    .size(30.dp)
                    .clip(CircleShape)
                    .border(width = 1.dp, color = verde, shape = RoundedCornerShape(15.dp))
                    .background(Color.Green)
            )

        }else{

            Box(
                Modifier
                    .padding(4.dp)
                    .size(30.dp)
                    .clip(CircleShape)
                    .border(width = 1.dp, color = verde, shape = RoundedCornerShape(15.dp))
                    .background(Color.Red)
            )
            Text(
                modifier = Modifier.padding(top = 10.dp, end = 20.dp),
                text = "OFF",
                fontFamily = PoppinsR,
                fontSize = 15.sp,
                color = Color.Red,
                fontWeight = FontWeight.Medium
            )

        }

    }
}
