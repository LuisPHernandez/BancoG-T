package com.example.bancogt.navigation

sealed class Route(val route: String) {

    data object Home: Route("home")

}