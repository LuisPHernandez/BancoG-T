package com.example.bancogt.ui.features.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import com.example.bancogt.ui.theme.BluePrimary
import com.example.bancogt.ui.theme.GraySecondary
import com.example.bancogt.ui.theme.WhiteBackground
import com.example.bancogt.ui.theme.GrayOnSecondary
import com.example.bancogt.ui.components.BottomNavigationBar

data class MenuItemUi(val label: String, val icon: ImageVector)

private val menuItemsExact = listOf(
    MenuItemUi("Configuración", Icons.Outlined.Settings),
    MenuItemUi("Ticket GTC", Icons.Outlined.ConfirmationNumber),
    MenuItemUi("Billeteras electrónicas", Icons.Outlined.AccountBalanceWallet),
    MenuItemUi("Servicios", Icons.Outlined.MiscellaneousServices),
    MenuItemUi("Solicitud de productos", Icons.Outlined.AddShoppingCart),
    MenuItemUi("Gestiones", Icons.Outlined.AssignmentTurnedIn),
    MenuItemUi("Atención Virtual", Icons.Outlined.HeadsetMic),
    MenuItemUi("Ahorros", Icons.Outlined.Savings),
    MenuItemUi("Gastos", Icons.Outlined.BarChart),
    MenuItemUi("Adelanto salario", Icons.Outlined.RequestQuote),
    MenuItemUi("Ubicaciones", Icons.Outlined.LocationOn),
)

@Composable
fun MenuFramedScreen(
    items: List<MenuItemUi> = menuItemsExact,
    bottomBar: @Composable () -> Unit = {},
    onItemClick: (MenuItemUi) -> Unit = {}
) {
    Scaffold(bottomBar = bottomBar) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(BluePrimary),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "Menú",
                    color = WhiteBackground,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(GraySecondary)
            ) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 12.dp),
                    color = WhiteBackground,
                    shape = MaterialTheme.shapes.large,
                    tonalElevation = 0.dp,
                    shadowElevation = 2.dp
                ) {
                    LazyColumn(contentPadding = PaddingValues(vertical = 8.dp)) {
                        items(items) { item ->
                            MenuRow(item) { onItemClick(item) }
                            Divider(color = GrayOnSecondary)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun MenuRow(item: MenuItemUi, onClick: () -> Unit) {
    ListItem(
        headlineContent = { Text(item.label, maxLines = 1, color = BluePrimary) },
        leadingContent  = { Icon(item.icon, contentDescription = item.label, tint = BluePrimary) },
        colors = ListItemDefaults.colors(containerColor = WhiteBackground),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 56.dp)
            .padding(horizontal = 12.dp)
            .clickable(onClick = onClick)
    )
}

@Preview(showBackground = true, showSystemUi = true, name = "Menú con marco (header fuera)")
@Composable
private fun MenuFramedScreenPreview() {
    MaterialTheme {
        MenuFramedScreen(
            items = menuItemsExact,
            bottomBar = { BottomNavigationBar() }
        )
    }
}