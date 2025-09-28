package com.example.bancogt.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bancogt.ui.features.home.HomeScreen
import com.example.bancogt.ui.features.login.LoginScreen

@Composable
fun AppNavigation(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Login.route
    ){
        composable(Screens.Login.route){
            LoginScreen()
        }
        composable(Screens.Home.route){
            HomeScreen()
        }
    }
}