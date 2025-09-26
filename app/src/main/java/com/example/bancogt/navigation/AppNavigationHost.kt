package com.example.bancogt.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun AppNavigationHost(navController: NavHostController){
    NavHost(
        navController = navController,
        startDestination = Route.Home.route
    ){
        composable(Route.Home.route){
            HomeScreen(navController)
        }

    }
}