package com.example.bancogt.ui.features.pagos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bancogt.navigation.Screens
import com.example.bancogt.ui.components.BottomNavigationBar
import com.example.bancogt.ui.features.home.HomeTopBar
import com.example.bancogt.ui.features.login.LoginViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PagosScreen(
    navController: NavController,
    viewModel: LoginViewModel
) {
    val pagoOptions = listOf(
        PagoOption("Cobro de Remesas", Icons.Filled.AccountBalance),
        PagoOption("Tarjeta de Crédito", Icons.Filled.CreditCard),
        PagoOption("Declaraguate", Icons.Filled.Description),
        PagoOption("Servicios", Icons.Filled.Receipt),
        PagoOption("Préstamos", Icons.Filled.MonetizationOn),
        PagoOption("Pago QR", Icons.Filled.QrCode)
    )

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
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5)) // Fondo gris claro como GTC
                .padding(paddingValues)
        ) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(pagoOptions) { option ->
                    PagoCard(
                        title = option.title,
                        icon = option.icon,
                        onClick = option.onClick
                    )
                }
            }
        }
    }
}


