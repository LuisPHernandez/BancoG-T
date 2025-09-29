package com.example.bancogt.navigation

sealed class Screens(val route: String) {

    data object Home: Screens("home")
    data object Login: Screens("login")
    data object Pagos: Screens("pagos")
    data object Register: Screens("register")
    data object Transferencias: Screens("transferencias")


}