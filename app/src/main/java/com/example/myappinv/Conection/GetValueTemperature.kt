package com.example.myappinv.Conection


import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

@Composable
fun getValueTemperature(): String {

    var valor by remember{ mutableStateOf("18")}
    var myRef: DatabaseReference = FirebaseDatabase.getInstance().reference
    var Temperatura by remember{ mutableStateOf(0)}

    myRef.child("/UsersData/-N5ltsvqedvxYUSP8k1h/products/-N5m9I02pmsdrHPdtD1y/Lecturas/TemperaturaAmbiente").addValueEventListener(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val value = dataSnapshot.getValue<String>()
            Temperatura= value!!.toInt()
            Log.d("axx", "Value is: $value")
            valor = Temperatura.toString()
        }
        override fun onCancelled(error: DatabaseError) {
            Log.w("axx", "Falla en lectura de datos.", error.toException())
        }
    })

    return valor

}