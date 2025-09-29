package com.example.bancogt.ui.features.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBalanceWallet
import androidx.compose.material.icons.outlined.AddShoppingCart
import androidx.compose.material.icons.outlined.AssignmentTurnedIn
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.ConfirmationNumber
import androidx.compose.material.icons.outlined.HeadsetMic
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.MiscellaneousServices
import androidx.compose.material.icons.outlined.RequestQuote
import androidx.compose.material.icons.outlined.Savings
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.bancogt.navigation.Screens
import com.example.bancogt.ui.components.BottomNavigationBar
import com.example.bancogt.ui.features.home.HomeTopBar
import com.example.bancogt.ui.features.login.LoginViewModel
import com.example.bancogt.ui.theme.BluePrimary
import com.example.bancogt.ui.theme.GrayOnSecondary
import com.example.bancogt.ui.theme.GraySecondary
import com.example.bancogt.ui.theme.WhiteBackground

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
    viewModel: LoginViewModel,
    navController: NavController,
    onItemClick: (MenuItemUi) -> Unit = {}
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
        bottomBar = {BottomNavigationBar(navController)}
    ) { innerPadding ->
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