package com.example.myappinv.Conection


import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue

@Composable
fun getStateValue(s: String): Any {
    var myRef: DatabaseReference = FirebaseDatabase.getInstance().reference
    var humedadSuelo by remember{ mutableStateOf(0)}

    myRef.child("/UsersData/-N5ltsvqedvxYUSP8k1h/products/-N5m9I02pmsdrHPdtD1y/SensoresEstado/$s").addValueEventListener(object : ValueEventListener {
        override fun onDataChange(dataSnapshot: DataSnapshot) {
            val value = dataSnapshot.getValue<String>()
            humedadSuelo = value!!.toInt()
            Log.d("ax", "Valor humedad es: $value")
        }
        override fun onCancelled(error: DatabaseError) {
            Log.w("ax", "Falla en lectura de datos.", error.toException())
        }
    })
    return humedadSuelo
}
