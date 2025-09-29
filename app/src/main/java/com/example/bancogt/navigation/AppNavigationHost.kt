package com.example.bancogt.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bancogt.ui.features.home.HomeScreen
import com.example.bancogt.ui.features.login.LoginScreen
import com.example.bancogt.ui.features.login.LoginViewModel
import com.example.bancogt.ui.features.menu.MenuFramedScreen
import com.example.bancogt.ui.features.pagos.PagosScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
    loginViewModel: LoginViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screens.Login.route
    ){
        composable(Screens.Login.route){
            LoginScreen(viewModel = loginViewModel, navController = navController)
        }
        composable(Screens.Home.route){
            HomeScreen(viewModel = loginViewModel, navController = navController)
        }
        composable(Screens.Pagos.route){
            PagosScreen(viewModel = loginViewModel, navController = navController)
        }
        composable(Screens.Menu.route){
            MenuFramedScreen(viewModel = loginViewModel, navController = navController)
        }

        /*
        composable(Screens.Transferencias.route){
            TransferenciasScreen()
        }
        */
    }
}