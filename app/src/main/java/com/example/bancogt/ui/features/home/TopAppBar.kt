package com.example.bancogt.ui.features.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.bancogt.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar(onLogout: () -> Unit) {
    CenterAlignedTopAppBar(
        title = {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = "Logo Banco",
                modifier = Modifier.height(32.dp),
                contentScale = ContentScale.Fit
            )
        },
        actions = {
            IconButton(onClick = onLogout) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.Logout,
                    contentDescription = "Cerrar sesión"
                )
            }
        },
        navigationIcon = {}
    )
}