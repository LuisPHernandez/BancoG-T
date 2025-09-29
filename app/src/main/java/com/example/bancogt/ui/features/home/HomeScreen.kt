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
        bottomBar = { BottomNavigationBar(navController) },
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Bienvenido, ${viewModel.user}",
                    style = MaterialTheme.typography.headlineMedium
                )
            }

            // Dos cuentas de ejemplo
            BalanceCard(accountType = true,  balance = 1000.0, id = 123456, owner = viewModel.user)
            BalanceCard(accountType = false, balance = 250.5,  id = 987654, owner = viewModel.user)
        }
    }

}