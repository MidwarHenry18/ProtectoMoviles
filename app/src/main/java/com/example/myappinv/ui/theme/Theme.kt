package com.example.myappinv.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = oscuro,
    primaryVariant = backgroundPrimary,
    secondary = oscuro,
    onPrimary = amarrillo,
    onBackground = blanco,
    background = negro,
    onSurface = blanco

)

private val LightColorPalette = lightColors(
    primary = backgroundPrimary,
    primaryVariant = barColor,
    secondary = blanco,
    onPrimary = blanco,
    onBackground = negro,
    background = blancob,
    onSurface = negro,
    surface = backgroundPrimary

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun MyAppInvTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}