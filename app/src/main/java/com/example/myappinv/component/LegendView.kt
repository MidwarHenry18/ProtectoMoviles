package com.example.myappinv.component


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myappinv.R
import com.example.myappinv.ui.theme.PoppinsR
import com.example.myappinv.ui.theme.Purple500
import com.example.myappinv.ui.theme.verde


@Composable
fun LegendView(){
    Surface(
        elevation = 6.dp,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .padding(bottom = 0.dp)
            .background(MaterialTheme.colors.background)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colors.secondary),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            StatictisIndicatorUI()
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
fun StatictisIndicatorUI(){
    Row(
        modifier = Modifier
            .height(40.dp)
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
            .background(MaterialTheme.colors.secondary),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IndicatorItemUI(text = "Actual")
        IndicatorItemUI(text = "Eficaz", color = verde)
        IndicatorItemUI(text = "Máxima", color = Color(0xFFE3E5E7))
    }
}
@Composable
fun IndicatorItemUI(text:String, color: Color = Purple500){
    Row{
        Icon(
            painter = painterResource(id = R.drawable.ic_hum1),
            contentDescription = "",
            tint = color,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = text,
            fontFamily = PoppinsR,
            fontSize = 12.sp,
            color = Color(0xFF818181),
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(start = 7.dp)
        )
    }
}