package com.example.myappinv.model


import androidx.annotation.DrawableRes
import com.example.myappinv.R

sealed class servicesItem(
    var name:String,
    @DrawableRes var id: Int,
    var descrption:String
) {

    object temperatura:servicesItem(
        "Control de Temperatura",
        R.drawable.ic_nube,
        "Clima"
    )
    object huemdad_suelo:servicesItem(
        "control de Humedad",
        R.drawable.ic_temin,
        "Temperatura"
    )
    object huemedad_ambiente:servicesItem(
        "Control de humedad",
        R.drawable.ic_suelo,
        "Humedad"
    )
    object riego:servicesItem(
        "Control de riego",
        R.drawable.ic_riego,
        "Riego"
    )
}

