package com.example.myappinv.screen

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myappinv.R
import com.example.myappinv.component.TextHeaderTitle
import com.example.myappinv.model.servicesItem
import com.example.myappinv.ui.theme.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.messaging.FirebaseMessaging

@Composable
fun Home_screen(navController: NavController, navInit: NavController){

    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background)
    ){
        HeaderHomeUI(navController,navInit)
        MessageActive()
        BodyHomeUI()
    }
}

@Composable
fun MessageActive() {
    val context = LocalContext.current
    var myRefm: DatabaseReference = FirebaseDatabase.getInstance().reference
    FirebaseMessaging.getInstance().token
        .addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.d("FCM Token", "falla de message", task.exception)
                return@OnCompleteListener
            }

            //Get new FCM registration token
            val token: String? = task.result
            Log.d("FCM Token", token, task.exception)
            //Toast.makeText(context, token, Toast.LENGTH_SHORT).show()
            myRefm.child("/UsersData/-N5ltsvqedvxYUSP8k1h/Token").setValue(token)
        })
}

@Composable
fun HeaderHomeUI(navController: NavController, navInit: NavController) {
    val context = LocalContext.current
    val save: SharedPreferences = context.getSharedPreferences("midwar", Context.MODE_PRIVATE)
    val sharedIdValue = save.getString("id_key","hola")
    TextHeaderTitle(sharedIdValue.toString(),"", navController = navController,navInit)
}

@Composable
fun BodyHomeUI() {
    Box(
        Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(10.dp))
            .background(MaterialTheme.colors.secondary)

    ) {
        Column (
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){

            Image(
                painter = painterResource(id = R.drawable.banner),
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(230.dp)
            )

            Text(
                text = "Green House",
                fontFamily = PoppinsM,
                fontSize = 30.sp,
                color = MaterialTheme.colors.onBackground,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center
            )
        }
    }

    var itemServices = listOf(
        servicesItem.temperatura,
        servicesItem.huemdad_suelo,
        servicesItem.huemedad_ambiente,
        servicesItem.riego

    )
    Row(modifier = Modifier.padding(start = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        Image(
            painter = painterResource(id = R.drawable.ic_vineta),
            contentDescription = "",
            modifier = Modifier
                .size(25.dp)
        )

        Text(
            text = "Servicios",
            fontFamily = PoppinsM,
            fontSize = 20.sp,
            color = MaterialTheme.colors.onBackground,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 6.dp)
        )
    }


    LazyVerticalGridServices(item = itemServices)
    //Spacer(modifier = Modifier.height(10.dp))

}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LazyVerticalGridServices(item:List<servicesItem>){
    val list = (1..10).map { it.toString() }
    val sda = item.map{it}

    LazyVerticalGrid(
        cells = GridCells.Adaptive(185.dp),

        // content padding
        contentPadding = PaddingValues(
            start = 16.dp,
            top = 16.dp,
            end = 16.dp,
            bottom = 16.dp
        ),
        content = {
            item.forEach{index ->
                item{
                    Surface(
                        elevation = 6.dp,
                        shape = RoundedCornerShape(10.dp),
                        modifier = Modifier.padding(4.dp)
                    ) {
                        Card(
                            border = BorderStroke(0.5.dp, Color.LightGray),
                            backgroundColor = MaterialTheme.colors.secondary,
                            elevation = 8.dp

                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(7.dp),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    painter = painterResource(id = index.id),
                                    contentDescription = "",
                                    modifier = Modifier.size(80.dp).padding(top=5.dp)
                                )
                                Spacer(modifier = Modifier.height(6.dp))
                                Text(
                                    text = index.descrption,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp,
                                    color = Color(0xFF868686),
                                    textAlign = TextAlign.Center
                                )
                            }

                        }
                    }
                    Spacer(modifier = Modifier.height(7.dp))
                    Spacer(modifier = Modifier.width(5.dp))

                }
            }
        }
    )
}
