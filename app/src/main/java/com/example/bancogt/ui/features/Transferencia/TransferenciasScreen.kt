package com.example.bancogt.ui.features.transferencias

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.bancogt.navigation.Screens
import com.example.bancogt.ui.components.BottomNavigationBar
import com.example.bancogt.ui.features.home.HomeTopBar
import com.example.bancogt.ui.features.login.LoginViewModel
import com.example.bancogt.ui.theme.BluePrimary

@Composable
fun TransferenciasScreen(
    navController: NavController,
    viewModel: LoginViewModel
) {
    var cuentaOrigen by remember { mutableStateOf("") }
    var cuentaDestino by remember { mutableStateOf("") }
    var monto by remember { mutableStateOf("") }
    var comentarios by remember { mutableStateOf("") }
    var mostrarDialogOrigen by remember { mutableStateOf(false) }
    var mostrarDialogDestino by remember { mutableStateOf(false) }

    // Cuentas de ejemplo
    val cuentasDisponibles = listOf(
        "Cuenta de Ahorros ****1234",
        "Cuenta Monetaria ****5678",
        "Cuenta Dólares ****3456"
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
                .background(Color(0xFFF5F5F5))
                .padding(paddingValues)
        ) {

            // Contenido con scroll
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 16.dp, vertical = 24.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                // Transferir de
                Text(
                    text = "Transferir de",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                
                SelectableField(
                    value = cuentaOrigen.ifEmpty { "Selecciona la cuenta a debitar" },
                    onClick = { mostrarDialogOrigen = true },
                    isEmpty = cuentaOrigen.isEmpty()
                )

                // Acreditar a
                Text(
                    text = "Acreditar a",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                
                SelectableField(
                    value = cuentaDestino.ifEmpty { "Selecciona la cuenta a acreditar" },
                    onClick = { mostrarDialogDestino = true },
                    isEmpty = cuentaDestino.isEmpty()
                )

                // Monto
                Text(
                    text = "Monto",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                
                OutlinedTextField(
                    value = monto,
                    onValueChange = { newValue ->
                        // Solo permite números y un punto decimal
                        if (newValue.isEmpty() || newValue.matches(Regex("^\\d*\\.?\\d{0,2}\$"))) {
                            monto = newValue
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    placeholder = { 
                        Text(
                            "GTQ 00.00",
                            color = Color.Gray.copy(alpha = 0.5f)
                        ) 
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal
                    ),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = BluePrimary,
                        unfocusedBorderColor = Color.LightGray,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                    shape = RoundedCornerShape(8.dp),
                    singleLine = true
                )

                // Comentarios
                Text(
                    text = "Comentarios",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
                
                OutlinedTextField(
                    value = comentarios,
                    onValueChange = { comentarios = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    placeholder = { 
                        Text(
                            "Comentarios",
                            color = Color.Gray.copy(alpha = 0.5f)
                        ) 
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = BluePrimary,
                        unfocusedBorderColor = Color.LightGray,
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    ),
                    shape = RoundedCornerShape(8.dp),
                    maxLines = 5
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Botón Transferir
                Button(
                    onClick = {
                        // Validar y procesar transferencia
                        if (cuentaOrigen.isNotEmpty() && 
                            cuentaDestino.isNotEmpty() && 
                            monto.isNotEmpty()) {
                            // Aquí iría la lógica de transferencia
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = BluePrimary,
                        contentColor = Color.White
                    ),
                    shape = RoundedCornerShape(8.dp),
                    enabled = cuentaOrigen.isNotEmpty() && 
                              cuentaDestino.isNotEmpty() && 
                              monto.isNotEmpty()
                ) {
                    Text(
                        text = "Transferir ahora",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }

    // Dialog para seleccionar cuenta origen
    if (mostrarDialogOrigen) {
        AlertDialog(
            onDismissRequest = { mostrarDialogOrigen = false },
            title = { Text("Selecciona cuenta origen") },
            text = {
                Column {
                    cuentasDisponibles.forEach { cuenta ->
                        TextButton(
                            onClick = {
                                cuentaOrigen = cuenta
                                mostrarDialogOrigen = false
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = cuenta,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Start,
                                color = BluePrimary
                            )
                        }
                        Divider()
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { mostrarDialogOrigen = false }) {
                    Text("Cancelar", color = BluePrimary)
                }
            }
        )
    }

    // Dialog para seleccionar cuenta destino
    if (mostrarDialogDestino) {
        AlertDialog(
            onDismissRequest = { mostrarDialogDestino = false },
            title = { Text("Selecciona cuenta destino") },
            text = {
                Column {
                    cuentasDisponibles.forEach { cuenta ->
                        TextButton(
                            onClick = {
                                cuentaDestino = cuenta
                                mostrarDialogDestino = false
                            },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = cuenta,
                                modifier = Modifier.fillMaxWidth(),
                                textAlign = TextAlign.Start,
                                color = BluePrimary
                            )
                        }
                        Divider()
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { mostrarDialogDestino = false }) {
                    Text("Cancelar", color = BluePrimary)
                }
            }
        )
    }
}

@Composable
fun SelectableField(
    value: String,
    onClick: () -> Unit,
    isEmpty: Boolean
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(8.dp),
        color = Color.White,
        border = androidx.compose.foundation.BorderStroke(
            1.dp, 
            Color.LightGray
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = value,
                color = if (isEmpty) Color.Gray.copy(alpha = 0.5f) else Color.Black,
                fontSize = 16.sp,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = "Seleccionar",
                tint = Color.Gray
            )
        }
    }
}
