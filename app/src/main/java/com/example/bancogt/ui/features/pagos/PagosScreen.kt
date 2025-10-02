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
@Composable // construir interfaces
fun PagosScreen( //pantalla recibe 2 parametros
    viewModel: LoginViewModel, //Para acceder al usuario y poder hacer logout
    navController: NavController //Para navegar entre pantallas
) {

    // Estado del Bottom Sheet
    var showQRBottomSheet by remember { mutableStateOf(false) } //var si qr visible o nel
    val sheetState = rememberModalBottomSheetState()//Estado del Modal Bottom Sheet (para animaciones)
    val scope = rememberCoroutineScope() //Para ejecutar operaciones asíncronas (como cerrar el sheet)

    val pagoOptions = listOf( //lista de opciones de pago

        //en donde cada opcion tiene titulo y icono
        PagoOption("Cobro de Remesas", Icons.Filled.AccountBalance), //iconos de las opciones
        PagoOption("Tarjeta de Crédito", Icons.Filled.CreditCard),
        PagoOption("Declaraguate", Icons.Filled.Description),
        PagoOption("Servicios", Icons.Filled.Receipt),
        PagoOption("Préstamos", Icons.Filled.MonetizationOn),
        PagoOption("Pago QR", Icons.Filled.QrCode, onClick = { // solo pagoQR tiene accion, solo para representar el QR
            showQRBottomSheet = true //aqui se activa el bottomSheet
        })
    )

    Scaffold( // Scaffold con TopBar y BottomBar, scaffold es tecnica que genera automáticamente la estructura básica de una aplicación

        topBar = { //Muestra el logo del banco y botón de logout
            HomeTopBar {
                //va  a tener la logica del logout
                viewModel.user = "" //limpian las variables del viewModel
                viewModel.password = ""
                viewModel.checked = false
                navController.navigate(Screens.Login.route) {
                    popUpTo(0) { inclusive = true }
                }
            }
        },
        bottomBar = { //Barra de navegación inferior para cambiar entre secciones
            BottomNavigationBar(navController)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
                .padding(paddingValues)
        ) {
            LazyVerticalGrid( //un tipo de lazy components Grid de Tarjetas de Pago
                columns = GridCells.Fixed(2), // van a ser 2 columnas
                contentPadding = PaddingValues(16.dp), //espacio entre tarjetas
                horizontalArrangement = Arrangement.spacedBy(12.dp), //espacio entre tarjetas
                verticalArrangement = Arrangement.spacedBy(12.dp),//espacio entre tarjetas
                modifier = Modifier.fillMaxSize() //tamaño del grid completo (toda la pantalla)
            ) {
                items(pagoOptions) { option ->
                    PagoCard(
                        title = option.title,
                        icon = option.icon,
                        onClick = option.onClick // ← Ejecuta la acción de cada card, en este caso, el onClick solo seria para el qr
                    )
                }
            }
        }

        // ModalBottomSheet para Pago QR que se hizo
        if (showQRBottomSheet) { // Solo se muestra si esta variable es true
            ModalBottomSheet( //se muestra el bottom sheet,omponente que aparece sobre el contenido actual, como un drawer o cajón que se desliza desde la parte inferior de la pantalla.
                onDismissRequest = { showQRBottomSheet = false }, // Cerrar al tocar fuera
                sheetState = sheetState,
                // ↓ El componente automáticamente se desliza desde abajo
                containerColor = Color(0xFFE3F2FD) // Fondo azul claro
            ) {
                QROptionsBottomSheet(
                    onDismiss = { // Cerrar al tocar fuera
                        scope.launch { //Ejecutar corrutina,operaciones corren en segundo plano, Son tareas que toman tiempo en ejecutarse
                            sheetState.hide() // Cerrar el bottom sheet
                            showQRBottomSheet = false // Cerrar el bottom sheet
                        }
                    }
                )
            }
        }
    }
}

@Composable
//Contenido del Bottom Sheet
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
fun QROptionsBottomSheetPreview() { //preview del bottom sheet
    MaterialTheme {
        QROptionsBottomSheet()
    }
}