package com.example.bancogt.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Cached
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Payment
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

data class NavItem(
    val label: String,
    val icon: ImageVector,
    val badgeCount: Int? = null
)

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    val items: LinkedHashMap<NavItem, String> = linkedMapOf(
        NavItem("Inicio", Icons.Filled.Home) to "home",
        NavItem("Pagos", Icons.Outlined.Payment) to "pagos",
        NavItem("Transferir", Icons.Outlined.Cached) to "transferencias",
        NavItem("Menú", Icons.Outlined.Menu) to "menu"
    )

    NavigationBar {
        items.forEach { (item, route) ->
            val selected = currentRoute == route

            NavigationBarItem(
                selected = selected,
                onClick = { navController.navigate(route) },
                icon = {
                    if (item.badgeCount != null) {
                        BadgedBox(badge = { Badge { Text("${item.badgeCount}") } }) {
                            Icon(item.icon, contentDescription = item.label)
                        }
                    } else {
                        Icon(item.icon, contentDescription = item.label)
                    }
                },
                label = { Text(item.label) }
            )
        }
    }
}
