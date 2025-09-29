package com.example.bancogt.ui.features.pagos

import androidx.compose.ui.graphics.vector.ImageVector

data class PagoOption(
    val title: String,
    val icon: ImageVector,
    val onClick: () -> Unit = {}
)