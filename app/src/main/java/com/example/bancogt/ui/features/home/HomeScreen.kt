package com.example.bancogt.ui.features.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bancogt.navigation.Screens
import com.example.bancogt.ui.components.BottomNavigationBar
import com.example.bancogt.ui.features.login.LoginViewModel
import kotlin.random.Random

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: LoginViewModel
) {
    Scaffold(
        topBar = {
            HomeTopBar {
                viewModel.user = ""
                viewModel.password = ""
                viewModel.checked = false
                navController.navigate(Screens.Login.route) {
                    popUpTo(0) { inclusive = true }
                }
            }
        },
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text("Bienvenido, ${viewModel.user}", style = MaterialTheme.typography.headlineMedium)

                for (i in 1..2) {
                    BalanceCard(credit = true, balance = 1000.00, id = Random.nextInt(1,1000000))
                }
            }
        }
    }

}