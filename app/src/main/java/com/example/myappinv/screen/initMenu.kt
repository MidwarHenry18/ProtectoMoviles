package com.example.myappinv.screen

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.myappinv.component.BottonNavBar
import com.example.myappinv.model.menuItem
import com.example.myappinv.navegation.menuNavegation

@Composable
fun initMenu(navController_INIT: NavController) {
    val navControllerMenu = rememberNavController()
    val navItem = listOf(
        menuItem.screenHome,
        menuItem.screenControl,
        menuItem.screenHumed,
        menuItem.screenSetting
    )

    Scaffold(
        bottomBar = {
            BottonNavBar(
                navController = navControllerMenu,
                items = navItem
            )
        }
    ) {
        menuNavegation(navController = navControllerMenu,navController_INIT)
    }
}