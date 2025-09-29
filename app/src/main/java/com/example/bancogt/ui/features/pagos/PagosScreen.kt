package com.example.bancogt.ui.features.pagos

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.bancogt.navigation.Screens
import com.example.bancogt.ui.components.BottomNavigationBar
import com.example.bancogt.ui.features.home.HomeTopBar
import com.example.bancogt.ui.features.login.LoginViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PagosScreen(
    viewModel: LoginViewModel,
    navController: NavController
) {
    var showQRBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    val pagoOptions = listOf(
        PagoOption("Cobro de Remesas", Icons.Filled.AccountBalance),
        PagoOption("Tarjeta de Crédito", Icons.Filled.CreditCard),
        PagoOption("Declaraguate", Icons.Filled.Description),
        PagoOption("Servicios", Icons.Filled.Receipt),
        PagoOption("Préstamos", Icons.Filled.MonetizationOn),
        PagoOption("Pago QR", Icons.Filled.QrCode, onClick = {
            showQRBottomSheet = true
        })
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
            BottomNavigationBar(navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
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

        // ModalBottomSheet para Pago QR que se hizo
        if (showQRBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = { showQRBottomSheet = false },
                sheetState = sheetState,
                containerColor = Color(0xFFE3F2FD) // Azul muy claro
            ) {
                QROptionsBottomSheet(
                    onDismiss = {
                        scope.launch {
                            sheetState.hide()
                            showQRBottomSheet = false
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun QROptionsBottomSheet(
    onDismiss: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Título
        Text(
            text = "Haz tap para escanear un código QR",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp
            ),
            color = Color(0xFF424242),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        // Ícono QR grande
        Box(
            modifier = Modifier
                .size(80.dp)
                .background(Color.White, shape = RoundedCornerShape(40.dp)),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Filled.QrCode,
                contentDescription = "Código QR",
                modifier = Modifier.size(48.dp),
                tint = Color(0xFF1976D2)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Botón Seleccionar Imagen
        Button(
            onClick = { /* Acción para seleccionar imagen */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color(0xFF1976D2)
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.Image,
                contentDescription = "Seleccionar imagen",
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = "SELECCIONAR IMAGEN",
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 14.sp
                )
            )
        }

        // Texto formato permitido
        Text(
            text = "Formato permitido .jpg o .png",
            style = MaterialTheme.typography.bodySmall.copy(
                fontSize = 12.sp
            ),
            color = Color(0xFF757575),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PagosScreenPreview() {
    MaterialTheme {
        PagosScreen(
            viewModel = LoginViewModel(),
            navController = rememberNavController()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun QROptionsBottomSheetPreview() {
    MaterialTheme {
        QROptionsBottomSheet()
    }
}