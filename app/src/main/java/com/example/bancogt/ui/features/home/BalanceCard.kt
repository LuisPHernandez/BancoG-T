package com.example.bancogt.ui.features.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BalanceCard(credit: Boolean, balance: Double, id: Int){
    val text = when (credit){
        true -> "Crédito"
        false -> "Débito"
    }

    Card(
        modifier = Modifier.padding(vertical = 16.dp).width(150.dp)
    ){

    }
}
