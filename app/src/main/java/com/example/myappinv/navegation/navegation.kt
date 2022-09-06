package com.example.myappinv.navegation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myappinv.model.menuItem
import com.example.myappinv.screen.*

@Composable
fun navegation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "splash_page",
        builder = {
            composable("splash_page", content = { SplashPage(navController = navController)})
            composable("login_page", content = { LoginPage(navController = navController)})
            composable("register_page", content = { RegisterPage(navController = navController)})
            composable("start_Menu", content = { initMenu(navController)})
        }
    )

}
@Composable
fun menuNavegation(navController: NavHostController, navInit: NavController){
    NavHost(
        navController = navController,
        startDestination = menuItem.screenHome.route,
        builder = {
            composable(route = menuItem.screenHome.route){ Home_screen(navController,navInit)}
            composable(route = menuItem.screenControl.route){ Control_screen(navController,navInit)}
            composable(route = menuItem.screenHumed.route){ Humedad_screen(navController,navInit)}
            composable(route = menuItem.screenSetting.route){ DivicesState_screen(navController,navInit)}
            composable(route = "setting_page"){ Setting_screen(navController,navInit)}
            composable(route = "policy_page"){ PolicyContent(navController)}
            composable(route = "ifu_page"){ ifuContent(navController)}

        }
    )
}