package com.example.myappinv.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.myappinv.R
import kotlin.math.absoluteValue

sealed class menuItem(
    val  route: String,
    val title:String,
    val icon:ImageVector
) {
    object screenHome:menuItem(
        "scream_home",
        "Home",
        Icons.Filled.Home
    )
    object screenControl:menuItem(
        "scream_control",
        "Temper",
        Icons.Filled.Eco
    )
    object screenHumed:menuItem(
        "scream_humed",
        "Humedad",
        Icons.Filled.Opacity
    )
    object screenSetting:menuItem(
        "scream_setting",
        "Divices",
        Icons.Filled.OnlinePrediction
    )
}